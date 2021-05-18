package com.event.system.eventsystem.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.event.system.eventsystem.dto.AttendeeDTO.AttendeeDTOInsert;
import com.event.system.eventsystem.dto.AttendeeDTO.AttendeeDTOUpdate;
import com.event.system.eventsystem.utils.ValidationResult;

@Entity
@Table(name = "TB_ATTENDEE")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Attendee extends User {
   private Float balance;

   public Attendee() {

   }

   public Attendee(Float balance) {
      this.balance = balance;
   }

   public Attendee(String name, String email, Float balance) {
      super(name, email);
      this.balance = balance;
   }

   public Attendee(AttendeeDTOInsert attendeeDTOInsert){
      super(attendeeDTOInsert.getName(), attendeeDTOInsert.getEmail());
      this.balance = attendeeDTOInsert.getBalance();
   }

   public Float getBalance() {
      return balance;
   }

   public void setBalance(Float balance) {
      this.balance = balance;
   }

   public void setAttendeeToUpdate(AttendeeDTOUpdate attendeeDTOUpdate) {
      this.setEmail(attendeeDTOUpdate.getEmail());
      this.setBalance(attendeeDTOUpdate.getBalance());
   }

   public ValidationResult validate() {
      ValidationResult validationResult = new ValidationResult();

      validationResult = nameValidate(); 

      if(!validationResult.IsValid())
         return validationResult;
      
      return validationResult;
   }

   public ValidationResult nameValidate() {
      ValidationResult validationResult = new ValidationResult();

      if(this.getName().isEmpty())
         validationResult.setErrors("Error: Attendee name can't be empty!");
      
      return validationResult;
   }
   
   
}
