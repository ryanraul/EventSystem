package com.event.system.eventsystem.services;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.EventDTO;
import com.event.system.eventsystem.dto.EventDTOInsert;
import com.event.system.eventsystem.dto.EventDTOUpdate;
import com.event.system.eventsystem.entities.Admin;
import com.event.system.eventsystem.entities.Event;
import com.event.system.eventsystem.repositories.AdminRepository;
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

   @Autowired AdminRepository adminRepository;

   public Page<EventDTO> getEvents(PageRequest pageRequest, String name, String dateFilter, String description){
      try {
         LocalDate date = LocalDate.parse(dateFilter);
         Page<Event> events = repo.find(pageRequest, name, date, description);
         return events.map(e -> new EventDTO(e));
      } catch (DateTimeException exception) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date filter format is incorrect, the correct one is 'YYYY-MM-DD'");
      } catch (Exception exception) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
      }
   }

   public EventDTO getEventById(Long id){
      Optional<Event> eventOp = repo.findById(id);
      Event event = eventOp.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found."));

      return new EventDTO(event);
   }
   
   public EventDTO insertEvent(EventDTOInsert eventDTO){
      try {                  
         Event event = new Event(eventDTO);

         Optional<Admin> adminOp = adminRepository.findById(eventDTO.getAdminId());
         Admin admin = adminOp.orElseThrow( () -> new Exception("Admin id not found."));         
         event.setAdmin(admin);
         
         var validation = event.Validate();
         if(!validation.IsValid())
            throw new Exception(validation.errors.get(0).message);
         else
            event = repo.save(event);  

         return new EventDTO(event); 

      } catch (EmptyResultDataAccessException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
      
   }

   public EventDTO updateEvent(Long id, EventDTOUpdate eventDTOUpdate){
      try {
         Event event = repo.getOne(id);
         event.setEventToUpdate(eventDTOUpdate);
         
         var validation = event.Validate();
         if(!validation.IsValid())
            throw new Exception(validation.errors.get(0).message);
         event = repo.save(event);
         
         return new EventDTO(event);
      } catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
      } catch (Exception e) {
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
