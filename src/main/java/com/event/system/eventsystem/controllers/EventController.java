package com.event.system.eventsystem.controllers;

import java.util.List;

import com.event.system.eventsystem.dto.EventDTO;
import com.event.system.eventsystem.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
   
}
