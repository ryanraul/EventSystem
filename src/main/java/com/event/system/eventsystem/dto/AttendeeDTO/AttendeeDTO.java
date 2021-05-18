package com.event.system.eventsystem.dto.AttendeeDTO;

import com.event.system.eventsystem.entities.Attendee;

public class AttendeeDTO {
   private Long id;   
   private String name;   
   private String email;
   private Float balance;
   
   public AttendeeDTO(Attendee attendee) {
      this.id = attendee.getId();
      this.name = attendee.getName();
      this.email = attendee.getEmail();
      this.balance = attendee.getBalance();
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
   public Float getBalance() {
      return balance;
   }
   public void setBalance(Float balance) {
      this.balance = balance;
   }
}
