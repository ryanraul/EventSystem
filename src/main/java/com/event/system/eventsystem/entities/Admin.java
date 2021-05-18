package com.event.system.eventsystem.entities;

import com.event.system.eventsystem.dto.AdminDTO.AdminDTOInsert;
import com.event.system.eventsystem.dto.AdminDTO.AdminDTOUpdate;
import com.event.system.eventsystem.utils.ValidationResult;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ADMIN")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Admin extends User {
   private String phoneNumber;

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public Admin(){
      
   }

   public Admin(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
  
   public Admin(String name, String email, String phoneNumber) {
      super(name, email);
      this.phoneNumber = phoneNumber;
   }

   public Admin(AdminDTOInsert adminDTOInsert) {
      super(adminDTOInsert.getName(), adminDTOInsert.getEmail());
      this.phoneNumber = adminDTOInsert.getPhoneNumber();
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
         validationResult.setErrors("Error: Admin name can't be empty!");
      
      return validationResult;
   }

   public void setAdminToUpdate(AdminDTOUpdate adminDTOUpdate) {
      setEmail(adminDTOUpdate.getEmail());
      setPhoneNumber(adminDTOUpdate.getPhoneNumber());
   }
   

   
   
}
