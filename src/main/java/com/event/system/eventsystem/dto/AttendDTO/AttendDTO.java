package com.event.system.eventsystem.dto.AttendDTO;

import com.event.system.eventsystem.entities.Attend;

public class AttendDTO {
   private Long id;
   private String name;   
   private String email;
   private Float balance;
   
   public AttendDTO(Attend attend) {
      this.id = attend.getId();
      this.name = attend.getName();
      this.email = attend.getEmail();
      this.balance = attend.getBalance();
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
