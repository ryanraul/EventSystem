package com.event.system.eventsystem.controllers;

import java.net.URI;
import java.util.List;

import com.event.system.eventsystem.dto.AttendDTO.AttendDTO;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOInsert;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOUpdate;
import com.event.system.eventsystem.services.AttendService;

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
public class AttendCotroller {
   
   @Autowired
   private AttendService attendService;

   @GetMapping
   public ResponseEntity<List<AttendDTO>> getAttendees(){
      var attends = attendService.getAttendees();
      return ResponseEntity.ok(attends);
   }

   @GetMapping("{id}")
   public ResponseEntity<AttendDTO> getAttendById(@PathVariable Long id){
      var attend = attendService.getAttendById(id);
      return ResponseEntity.ok(attend);
   }

   @PostMapping
   public ResponseEntity<AttendDTO> insertAttend(@RequestBody AttendDTOInsert attendDTOInsert){
      AttendDTO attendDTO = attendService.insertAttend(attendDTOInsert);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(attendDTO.getId()).toUri();
      return ResponseEntity.created(uri).body(attendDTO);
   }

   @PutMapping("{id}")
   public ResponseEntity<AttendDTO> updateAttend(@RequestBody AttendDTOUpdate attendDTOUpdate, @PathVariable Long id){
      AttendDTO attendDTO = attendService.updateAttend(id, attendDTOUpdate);
      return ResponseEntity.ok().body(attendDTO);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Void> deleteAttend(@PathVariable Long id){
		attendService.deleteAttend(id); 
		return ResponseEntity.noContent().build();
	}   

}
