package com.event.system.eventsystem.services;

import java.util.ArrayList;
import java.util.List;

import com.event.system.eventsystem.dto.EventDTO;
import com.event.system.eventsystem.entities.Event;
import com.event.system.eventsystem.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
   
}
