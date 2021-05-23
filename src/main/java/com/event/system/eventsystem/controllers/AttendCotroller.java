package com.event.system.eventsystem.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.event.system.eventsystem.dto.AttendDTO.AttendDTO;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOInsert;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOUpdate;
import com.event.system.eventsystem.services.AttendService;

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
@RequestMapping("/attendees")
public class AttendCotroller {
   
   @Autowired
   private AttendService attendService;

   @GetMapping
   public ResponseEntity<Page<AttendDTO>> getAttendees(
      @RequestParam(value = "page",          defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage",  defaultValue = "6") Integer linesPerPage,
      @RequestParam(value = "direction",     defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy",       defaultValue = "id") String orderBy,
      @RequestParam(value = "name",          defaultValue = "") String name,
      @RequestParam(value = "email",         defaultValue = "") String email
   ){
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
      Page<AttendDTO> attendeesDTO = attendService.getAttendees(pageRequest, name, email);
      return ResponseEntity.ok(attendeesDTO);
   }

   @GetMapping("{id}")
   public ResponseEntity<AttendDTO> getAttendById(@PathVariable Long id){
      var attend = attendService.getAttendById(id);
      return ResponseEntity.ok(attend);
   }

   @PostMapping
   public ResponseEntity<AttendDTO> insertAttend(@Valid @RequestBody AttendDTOInsert attendDTOInsert){
      AttendDTO attendDTO = attendService.insertAttend(attendDTOInsert);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(attendDTO.getId()).toUri();
      return ResponseEntity.created(uri).body(attendDTO);
   }

   @PutMapping("{id}")
   public ResponseEntity<AttendDTO> updateAttend(@Valid @RequestBody AttendDTOUpdate attendDTOUpdate, @PathVariable Long id){
      AttendDTO attendDTO = attendService.updateAttend(id, attendDTOUpdate);
      return ResponseEntity.ok().body(attendDTO);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Void> deleteAttend(@PathVariable Long id){
		attendService.deleteAttend(id); 
		return ResponseEntity.noContent().build();
	}   

}
