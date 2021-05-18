package com.event.system.eventsystem.controllers;

import java.net.URI;
import java.util.List;

import com.event.system.eventsystem.dto.AttendeeDTO.AttendeeDTO;
import com.event.system.eventsystem.dto.AttendeeDTO.AttendeeDTOInsert;
import com.event.system.eventsystem.dto.AttendeeDTO.AttendeeDTOUpdate;
import com.event.system.eventsystem.services.AttendeeService;

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
@RequestMapping("/attendees")
public class AttendeeCotroller {
   
   @Autowired
   private AttendeeService attendeeService;

   @GetMapping
   public ResponseEntity<List<AttendeeDTO>> getAttendees(){
      var attendees = attendeeService.getAttendees();
      return ResponseEntity.ok(attendees);
   }

   @GetMapping("{id}")
   public ResponseEntity<AttendeeDTO> getAttendeeById(@PathVariable Long id){
      var attendee = attendeeService.getAttendeeById(id);
      return ResponseEntity.ok(attendee);
   }

   @PostMapping
   public ResponseEntity<AttendeeDTO> insertAttendee(@RequestBody AttendeeDTOInsert attendeeDTOInsert){
      AttendeeDTO attendeeDTO = attendeeService.insertAttendee(attendeeDTOInsert);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(attendeeDTO.getId()).toUri();
      return ResponseEntity.created(uri).body(attendeeDTO);
   }

   @PutMapping("{id}")
   public ResponseEntity<AttendeeDTO> updateAttendee(@RequestBody AttendeeDTOUpdate attendeeDTOUpdate, @PathVariable Long id){
      AttendeeDTO attendeeDTO = attendeeService.updateAttendee(id, attendeeDTOUpdate);
      return ResponseEntity.ok().body(attendeeDTO);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Void> deleteAttendee(@PathVariable Long id){
		attendeeService.deleteAttendee(id); 
		return ResponseEntity.noContent().build();
	}   

}
