package com.event.system.eventsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.EventDTO;
import com.event.system.eventsystem.dto.EventDTOInsert;
import com.event.system.eventsystem.dto.EventDTOUpdate;
import com.event.system.eventsystem.entities.Event;
import com.event.system.eventsystem.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {
   @Autowired EventRepository repo;

   public Page<EventDTO> getEvents(PageRequest pageRequest, String name, String place){
      Page<Event> events = repo.find(pageRequest, name, place);
      return events.map(e -> new EventDTO(e));
   }

   private List<EventDTO> toDTOList(List<Event> events) {
      List <EventDTO> eventsDTO = new ArrayList<>();

      for (Event event: events){
         EventDTO eventDTO = new EventDTO(event.getId(), event.getName(), event.getPlace());
         eventsDTO.add(eventDTO);
      }

      return eventsDTO;
   }

   public EventDTO getEventById(Long id){
      Optional<Event> eventOp = repo.findById(id);
      Event event = eventOp.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found."));

      return new EventDTO(event);
   }
   
   public EventDTO insertEvent(EventDTOInsert eventDTO){
      try {                  
         Event event = new Event(eventDTO);
         var validation = event.Validate();
         if(!validation.IsValid()){
            throw new Exception(validation.getMessage());
         }else{
            event = repo.save(event);       
         }
         return new EventDTO(event);
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   public EventDTO updateEvent(Long id, EventDTOUpdate eventDTO){
      try {
         Event event = repo.getOne(id);
         event.setDescription(eventDTO.getDescription());
         event.setPlace(eventDTO.getPlace());
         event.setStartDate(eventDTO.getStartDate());
         event.setEndDate(eventDTO.getEndDate());
         event.setStartTime(eventDTO.getStartTime());
         event.setEndTime(eventDTO.getEndTime());

         var validation = event.Validate();
         if(!validation.IsValid())
            throw new Exception(validation.getMessage());
         event = repo.save(event);
         return new EventDTO(event);
      }catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
      } 
      catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
      
   }

   public void deleteEvent(Long id){
      try {
          repo.deleteById(id);
      } catch (EmptyResultDataAccessException e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
      }
  }


}
