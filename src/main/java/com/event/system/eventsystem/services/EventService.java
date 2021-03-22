package com.event.system.eventsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.event.system.eventsystem.dto.EventDTO;
import com.event.system.eventsystem.dto.EventDTOInsert;
import com.event.system.eventsystem.entities.Event;
import com.event.system.eventsystem.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {
   @Autowired EventRepository repo;

   public List<EventDTO> getEvents(){
      List<Event> events = repo.findAll();
      return toDTOList(events);
   }

   private List<EventDTO> toDTOList(List<Event> events) {
      List <EventDTO> eventsDTO = new ArrayList<>();

      for (Event event: events){
         EventDTO eventDTO = new EventDTO(event.getId(), event.getName());
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
      Event event = new Event(eventDTO);
      event = repo.save(event);
      return new EventDTO(event);
   }

}
