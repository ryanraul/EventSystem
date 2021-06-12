package com.event.system.eventsystem.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.event.system.eventsystem.dto.AttendDTO.AttendDTOInsert;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOUpdate;

@Entity
@Table(name = "TB_ATTEND")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Attend extends User {
   private Float balance;

   @OneToMany(mappedBy = "attend", cascade = CascadeType.ALL)
   private List<Ticket> tickets = new ArrayList<>();

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
  
   
}
