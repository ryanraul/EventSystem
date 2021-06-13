package com.event.system.eventsystem.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.event.system.eventsystem.dto.AttendEventTicketDTO;
import com.event.system.eventsystem.dto.EventDTO;
import com.event.system.eventsystem.dto.EventDTOInsert;
import com.event.system.eventsystem.dto.EventDTOUpdate;
import com.event.system.eventsystem.dto.EventTicketDTO;
import com.event.system.eventsystem.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/events")
public class EventController {
   @Autowired
   private EventService service;

   @GetMapping
   public ResponseEntity<Page<EventDTO>> getEvents(
      @RequestParam(value = "page",          defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage",  defaultValue = "6") Integer linesPerPage,
      @RequestParam(value = "direction",     defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy",       defaultValue = "id") String orderBy,
      @RequestParam(value = "name",          defaultValue = "") String name,
      @RequestParam(value = "dateFilter",    defaultValue = "0001-01-01") String dateFilter,
      @RequestParam(value = "description",   defaultValue = "") String description
   ){
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
      Page<EventDTO> eventsDTO = service.getEvents(pageRequest, name, dateFilter, description);
      return ResponseEntity.ok(eventsDTO);
   }

   @GetMapping("{id}")
   public ResponseEntity<EventDTO> getEventById(@PathVariable Long id){
      EventDTO eventDTO = service.getEventById(id);
      return ResponseEntity.ok(eventDTO);
   }

   @PostMapping
   public ResponseEntity<EventDTO> insertEvent(@Valid @RequestBody EventDTOInsert eventDtoInsert){
      EventDTO eventDTO = service.insertEvent(eventDtoInsert);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventDTO.getId()).toUri();
      return ResponseEntity.created(uri).body(eventDTO);      
   }
   
   @PutMapping("{id}")
	public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody EventDTOUpdate eventDtoUpdate, @PathVariable Long id){
		EventDTO eventDTO = service.updateEvent(id, eventDtoUpdate); 
		return ResponseEntity.ok().body(eventDTO);
	}

   @DeleteMapping("{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
		service.deleteEvent(id); 
		return ResponseEntity.noContent().build();
	}

   @PostMapping("{eventId}/places/{placeId}")
   public ResponseEntity<EventDTO> insertPlaceEvent(@PathVariable Long eventId, @PathVariable Long placeId){

      EventDTO eventDTO = service.insertPlaceEvent(eventId, placeId);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventDTO.getId()).toUri();
      return ResponseEntity.created(uri).body(eventDTO);      
   }

   @DeleteMapping("{eventId}/places/{placeId}")
	public ResponseEntity<Void> deletePlaceEvent(@PathVariable Long eventId, @PathVariable Long placeId){
		service.deletePlaceEvent(eventId, placeId); 
		return ResponseEntity.noContent().build();
	}

   @GetMapping("{eventId}/tickets")
   public ResponseEntity<EventTicketDTO> getEventTickets(@PathVariable Long eventId){
      EventTicketDTO eventTickets = service.getEventTickets(eventId);
      return ResponseEntity.ok(eventTickets);
   }

   @PostMapping("{eventId}/tickets")
   public ResponseEntity<AttendEventTicketDTO> ticketSale(@PathVariable Long eventId, @RequestBody AttendEventTicketDTO attendTicket){

      AttendEventTicketDTO attendEvent = service.ticketSale(eventId, attendTicket);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(attendEvent.getAttendId()).toUri();
      return ResponseEntity.created(uri).body(attendEvent);      
   }

   @DeleteMapping("{eventId}/tickets")
	public ResponseEntity<Void> deleteEventTicket(@PathVariable Long eventId, @RequestBody AttendEventTicketDTO attendTicket){
		service.deleteEventTicket(eventId, attendTicket); 
		return ResponseEntity.noContent().build();
	}
   
}
