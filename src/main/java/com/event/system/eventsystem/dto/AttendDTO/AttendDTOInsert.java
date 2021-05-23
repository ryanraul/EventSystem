package com.event.system.eventsystem.dto.AttendDTO;

import javax.validation.constraints.NotBlank;

public class AttendDTOInsert {
   @NotBlank(message = "The 'name' cannot be empty!")
   private String name;

   @NotBlank(message = "The 'email' cannot be empty!")
   private String email;
   
   @NotBlank(message = "The 'balance' cannot be empty!")
   private Float balance;

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
