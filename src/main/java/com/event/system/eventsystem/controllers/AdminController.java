package com.event.system.eventsystem.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.event.system.eventsystem.dto.AdminDTO.AdminDTO;
import com.event.system.eventsystem.dto.AdminDTO.AdminDTOInsert;
import com.event.system.eventsystem.dto.AdminDTO.AdminDTOUpdate;
import com.event.system.eventsystem.services.AdminService;

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
@RequestMapping("/admins")
public class AdminController {
   
   @Autowired
   private AdminService adminService;

   @GetMapping
   public ResponseEntity<Page<AdminDTO>> getAdmins(
      @RequestParam(value = "page",          defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage",  defaultValue = "6") Integer linesPerPage,
      @RequestParam(value = "direction",     defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy",       defaultValue = "id") String orderBy,
      @RequestParam(value = "name",          defaultValue = "") String name,
      @RequestParam(value = "email",         defaultValue = "") String email
   ){
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
      Page<AdminDTO> adminsDTO = adminService.getAdmins(pageRequest, name, email);
      return ResponseEntity.ok(adminsDTO);
   }

   @GetMapping("{id}")
   public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id){
      var admin = adminService.getAdminById(id);
      return ResponseEntity.ok(admin);
   }

   @PostMapping
   public ResponseEntity<AdminDTO> insertAdmin(@Valid @RequestBody AdminDTOInsert adminDTOInsert){
      AdminDTO adminDTO = adminService.insertAdmin(adminDTOInsert);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(adminDTO.getId()).toUri();
      return ResponseEntity.created(uri).body(adminDTO);
   }

   @PutMapping("{id}")
   public ResponseEntity<AdminDTO> updateAdmin(@Valid @RequestBody AdminDTOUpdate adminDTOUpdate, @PathVariable Long id){
      AdminDTO adminDTO = adminService.updateAdmin(id, adminDTOUpdate);
      return ResponseEntity.ok().body(adminDTO);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Void> deleteAdmin(@PathVariable Long id){
		adminService.deleteAdmin(id); 
		return ResponseEntity.noContent().build();
	}   

}
