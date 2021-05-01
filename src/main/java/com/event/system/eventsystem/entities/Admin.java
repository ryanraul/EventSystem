package com.event.system.eventsystem.entities;

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
   

   
   
}
