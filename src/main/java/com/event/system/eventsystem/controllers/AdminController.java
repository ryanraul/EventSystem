package com.event.system.eventsystem.controllers;

import java.util.List;

import com.event.system.eventsystem.dto.AdminDTO.AdminDTO;
import com.event.system.eventsystem.services.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {
   
   @Autowired
   private AdminService adminService;

   @GetMapping
   public List<AdminDTO> getAdmins(){
      var admins = adminService.getAdmins();
      return admins;
   }

   @GetMapping("{id}")
   public AdminDTO getAdminById(@PathVariable Long id){
      var admin = adminService.getAdminById(id);
      return admin;
   }

}
