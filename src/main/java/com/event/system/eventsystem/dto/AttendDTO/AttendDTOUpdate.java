package com.event.system.eventsystem.dto.AttendDTO;

import javax.validation.constraints.NotBlank;

public class AttendDTOUpdate {
   @NotBlank(message = "The email cannot be empty!")
   private String email;

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
