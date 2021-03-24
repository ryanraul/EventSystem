package com.event.system.eventsystem.controllers;

import java.net.URI;
import java.util.List;

import com.event.system.eventsystem.dto.EventDTO;
import com.event.system.eventsystem.dto.EventDTOInsert;
import com.event.system.eventsystem.dto.EventDTOUpdate;
import com.event.system.eventsystem.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/events")
public class EventController {
   @Autowired
   private EventService service;

   @GetMapping
   public ResponseEntity<List<EventDTO>> getEvents(){
      List <EventDTO> eventsDTO = service.getEvents();
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
