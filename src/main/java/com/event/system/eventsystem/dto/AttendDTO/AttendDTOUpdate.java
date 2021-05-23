package com.event.system.eventsystem.dto.AttendDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AttendDTOUpdate {
   @NotBlank(message = "The email cannot be empty!")
   private String email;

   @NotNull(message = "The 'balance' cannot be empty!")
   private Float balance;

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
