package com.event.system.eventsystem.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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

   public Float getBalance() {
      return balance;
   }

   public void setBalance(Float balance) {
      this.balance = balance;
   }
   
   
}
