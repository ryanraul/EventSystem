package com.event.system.eventsystem.controllers;

import java.net.URI;

import com.event.system.eventsystem.dto.EventDTO;
import com.event.system.eventsystem.dto.EventDTOInsert;
import com.event.system.eventsystem.dto.EventDTOUpdate;
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
      @RequestParam(value = "place",         defaultValue = "") String place,
      @RequestParam(value = "dateFilter",    defaultValue = "0001-01-01") String dateFilter
   ){
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
      Page<EventDTO> eventsDTO = service.getEvents(pageRequest, name, place, dateFilter);
      return ResponseEntity.ok(eventsDTO);
   }

   @GetMapping("{id}")
   public ResponseEntity<EventDTO> getEventById(@PathVariable Long id){
      EventDTO eventDTO = service.getEventById(id);
      return ResponseEntity.ok(eventDTO);
   }

   @PostMapping
   public ResponseEntity<EventDTO> insertEvent(@RequestBody EventDTOInsert eventDtoInsert){
      EventDTO eventDTO = service.insertEvent(eventDtoInsert);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventDTO.getId()).toUri();
      return ResponseEntity.created(uri).body(eventDTO);      
   }
   
   @PutMapping("{id}")
	public ResponseEntity<EventDTO> updateEvent(@RequestBody EventDTOUpdate eventDtoUpdate, @PathVariable Long id){
		EventDTO eventDTO = service.updateEvent(id, eventDtoUpdate); 
		return ResponseEntity.ok().body(eventDTO);
	}

   @DeleteMapping("{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
		service.deleteEvent(id); 
		return ResponseEntity.noContent().build();
	}   
   
}
