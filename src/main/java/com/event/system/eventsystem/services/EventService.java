package com.event.system.eventsystem.services;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.AttendEventTicketDTO;
import com.event.system.eventsystem.dto.EventDTO;
import com.event.system.eventsystem.dto.EventDTOInsert;
import com.event.system.eventsystem.dto.EventDTOUpdate;
import com.event.system.eventsystem.dto.EventTicketDTO;
import com.event.system.eventsystem.dto.TicketDTO.AttendTicketDTO;
import com.event.system.eventsystem.entities.Admin;
import com.event.system.eventsystem.entities.Attend;
import com.event.system.eventsystem.entities.Event;
import com.event.system.eventsystem.entities.Place;
import com.event.system.eventsystem.entities.Ticket;
import com.event.system.eventsystem.entities.TicketType;
import com.event.system.eventsystem.repositories.AdminRepository;
import com.event.system.eventsystem.repositories.AttendRepository;
import com.event.system.eventsystem.repositories.EventRepository;
import com.event.system.eventsystem.repositories.PlaceRepository;
import com.event.system.eventsystem.repositories.TicketRepository;
import com.event.system.eventsystem.utils.ValidationResult;

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

   @Autowired PlaceRepository placeRepository;
   @Autowired AdminRepository adminRepository;
   @Autowired AttendRepository attendRepository;
   @Autowired TicketRepository ticketRepository;

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
         
         var validation = event.DatesAndTimesValidate();
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
         Optional<Event> eventOp = repo.findById(id);
         Event event = eventOp.orElseThrow(() -> new EntityNotFoundException("Event not found"));
         var validation = event.ValidateUpdate();

         if(!validation.IsValid())
            throw new Exception(validation.errors.get(0).message);
         
         event.setEventToUpdate(eventDTOUpdate);
         validation = event.DatesAndTimesValidate();
         if(!validation.IsValid())
            throw new Exception(validation.errors.get(0).message);

         event = repo.save(event);         
         return new EventDTO(event);
         
      } catch (EntityNotFoundException e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
      
   }

   public void deleteEvent(Long id){
      try {
          repo.deleteById(id);
      } catch (EmptyResultDataAccessException e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      } 
  }

  public EventDTO insertPlaceEvent(Long eventId, Long placeId){
      try {
         Optional<Event> eventOp = repo.findById(eventId);
         Event event = eventOp.orElseThrow(() -> new EntityNotFoundException("Event not found"));

         var validation = event.ValidateUpdate();

         if(!validation.IsValid())
            throw new Exception(validation.errors.get(0).message);

         Optional<Place> placeOp = placeRepository.findById(placeId);
         Place place = placeOp.orElseThrow(() -> new EntityNotFoundException("Place not found"));

         List<Event> eventsSamePlace = repo.findAll().stream()
                                          .filter(e -> e.getEndDate().isAfter(LocalDate.now()))
                                          .filter(e -> e.getPlaces().stream().filter(p-> p.getId() == placeId).count() != 0)
                                          .collect(Collectors.toList());
      
         validation = checkingAvailabilityEventsPlaces(event, eventsSamePlace);

         if(!validation.IsValid())
            throw new Exception(validation.errors.get(0).message);

         event.addPlace(place);
         event = repo.save(event);         
         return new EventDTO(event);
         
      } catch (EntityNotFoundException e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }   
   }

   public void deletePlaceEvent(Long eventId, Long placeId) {
      try {
         Optional<Event> eventOp = repo.findById(eventId);
         Event event = eventOp.orElseThrow(() -> new EntityNotFoundException("Event not found."));

         if(event.getEndDate().isBefore(LocalDate.now()))
            throw new Exception("This event has already happened.");

         Optional<Place> placeOp = placeRepository.findById(placeId);
         Place place = placeOp.orElseThrow(() -> new EntityNotFoundException("Place not found."));

         event.removePlace(place);
         event = repo.save(event); 

      } catch (EntityNotFoundException e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }  
   }

   private ValidationResult checkingAvailabilityEventsPlaces(Event event, List<Event> eventsSamePlace) {
      ValidationResult validationResult = new ValidationResult();

      for (Event eventSamePlace : eventsSamePlace) {
         if(eventSamePlace.getStartDate().isAfter(event.getEndDate()) && eventSamePlace.getEndDate().isBefore(event.getStartDate())){
            continue;
         }

         validationResult.setErrors("Error: This place is reserved for " + eventSamePlace.getName());
         return validationResult;         
      }
      return validationResult;
   }

   public AttendEventTicketDTO ticketSale(Long eventId, AttendEventTicketDTO attendTicket) {
      try {
         Optional<Event> eventOp = repo.findById(eventId);
         Event event = eventOp.orElseThrow(() -> new EntityNotFoundException("Event not found"));

         var validation = event.ValidateUpdate();

         if(!validation.IsValid())
            throw new Exception(validation.errors.get(0).message);

         Optional<Attend> attendOp = attendRepository.findById(attendTicket.getAttendId());
         Attend attend = attendOp.orElseThrow(() -> new EntityNotFoundException("Attend not found"));
         
         validation = ticketValidationProcessing(attendTicket, event, attend);

         if(!validation.IsValid())
            throw new Exception(validation.errors.get(0).message);

         
         Ticket ticket = new Ticket(attendTicket.getTicketType(), Instant.now(), event.getPriceTicket(),event, attend);
                  
         ticketRepository.save(ticket);
         attendRepository.save(attend);   
         repo.save(event);         
         return attendTicket;
         
      } catch (EntityNotFoundException e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   private ValidationResult ticketValidationProcessing(AttendEventTicketDTO attendTicket, Event event, Attend attend){
      ValidationResult validationResult = new ValidationResult();

      if(attendTicket.getTicketType() != TicketType.FREE){
         if(event.getAmountPayedTickets() <= 0){
            validationResult.setErrors("Paid tickets sold out.");
            return validationResult;
         }

         if(attend.getBalance() >= event.getPriceTicket()){
            attend.setBalance(attend.getBalance() - event.getPriceTicket());
            event.setAmountPayedTickets(event.getAmountPayedTickets() - 1);
            event.setPayedTickectsSelled(event.getPayedTickectsSelled() + 1);
         }else{
            validationResult.setErrors("Attend doesn't have enough balance.");
            return validationResult;
         }
      }else{
         if(event.getAmountFreeTickets() <= 0){
            validationResult.setErrors("Free tickets sold out.");
            return validationResult;
         }         
         event.setAmountFreeTickets(event.getAmountFreeTickets() - 1);
         event.setFreeTickectsSelled(event.getFreeTickectsSelled() + 1);            
      }
      return validationResult;
   }

   public void deleteEventTicket(Long eventId, AttendEventTicketDTO attendTicket) {
      try {
         Optional<Event> eventOp = repo.findById(eventId);
         Event event = eventOp.orElseThrow(() -> new EntityNotFoundException("Event not found."));

         if(event.getStartDate().isBefore(LocalDate.now()) || event.getStartDate().isEqual(LocalDate.now()))
            throw new Exception("Cannot return ticket, because event is currently happening.");

         Optional<Attend> attendOp = attendRepository.findById(attendTicket.getAttendId());
         Attend attend = attendOp.orElseThrow(() -> new EntityNotFoundException("Attend not found."));

         Ticket ticket = event.getTickets().stream()
                                             .filter(t -> t.getType() == attendTicket.getTicketType())
                                             .filter(t -> t.getAttend().getId() == attend.getId())
                                             .findFirst()
                                             .orElseThrow(() -> new EntityNotFoundException("Attend ticket not found."));
         
         if(attendTicket.getTicketType() != TicketType.FREE){
            attend.setBalance(attend.getBalance() + event.getPriceTicket());
            event.setAmountPayedTickets(event.getAmountPayedTickets() + 1);
            event.setPayedTickectsSelled(event.getPayedTickectsSelled() - 1);
         }else{
            event.setAmountFreeTickets(event.getAmountFreeTickets() + 1);
            event.setFreeTickectsSelled(event.getFreeTickectsSelled() - 1);
         }
         
         event.removeTicket(ticket);
         attend.removeTicket(ticket);
         ticketRepository.deleteById(ticket.getId());
         attendRepository.save(attend);   
         repo.save(event);   

      } catch (EntityNotFoundException e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }  
   }

   public EventTicketDTO getEventTickets(Long eventId) {
      try {
         Optional<Event> eventOp = repo.findById(eventId);
         Event event = eventOp.orElseThrow(() -> new EntityNotFoundException("Event not found."));
         
         var eventTickets = event.getTickets();
         EventTicketDTO eventTicketDTO = new EventTicketDTO();

         eventTicketDTO.setAttendTickets(
            eventTickets.stream()
                        .map(t -> new AttendTicketDTO(t.getAttend().getName(), t.getType()))
                        .collect(Collectors.toList())
         );

         eventTicketDTO.setAmountFreeTickets(event.getAmountFreeTickets());
         eventTicketDTO.setAmountPayedTickets(event.getAmountPayedTickets());
         eventTicketDTO.setAlreadyPaidAmountFreeTickets(eventTicketDTO.getAttendTickets().stream().filter(t -> t.getTicketType() == TicketType.FREE).count());
         eventTicketDTO.setAlreadyPaidAmountTickets(eventTicketDTO.getAttendTickets().stream().filter(t -> t.getTicketType() == TicketType.PAID).count());
         
         return eventTicketDTO;
         
      } catch (EntityNotFoundException e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }  
   }

}