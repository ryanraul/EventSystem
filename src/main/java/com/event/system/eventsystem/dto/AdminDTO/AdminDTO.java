package com.event.system.eventsystem.dto.AdminDTO;

import com.event.system.eventsystem.entities.Admin;

public class AdminDTO {
   private Long id;
   private String name;   
   private String email;
   private String phoneNumber;

   public AdminDTO(Admin admin) {
      this.id = admin.getId();
      this.name = admin.getName();
      this.email = admin.getEmail();
      this.phoneNumber = admin.getPhoneNumber();
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPhoneNumber() {
      return phoneNumber;
   }
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
}
