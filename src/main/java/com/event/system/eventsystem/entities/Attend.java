package com.event.system.eventsystem.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.event.system.eventsystem.dto.AttendDTO.AttendDTOInsert;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOUpdate;
import com.event.system.eventsystem.utils.ValidationResult;

@Entity
@Table(name = "TB_ATTEND")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Attend extends User {
   private Float balance;

   public Attend() {

   }

   public Attend(Float balance) {
      this.balance = balance;
   }

   public Attend(String name, String email, Float balance) {
      super(name, email);
      this.balance = balance;
   }

   public Attend(AttendDTOInsert attendeeDTOInsert){
      super(attendeeDTOInsert.getName(), attendeeDTOInsert.getEmail());
      this.balance = attendeeDTOInsert.getBalance();
   }

   public Float getBalance() {
      return balance;
   }

   public void setBalance(Float balance) {
      this.balance = balance;
   }

   public void setAttendToUpdate(AttendDTOUpdate attendDTOUpdate) {
      this.setEmail(attendDTOUpdate.getEmail());
      this.setBalance(attendDTOUpdate.getBalance());
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
         validationResult.setErrors("Error: Attend name can't be empty!");
      
      return validationResult;
   }
   
   
}
