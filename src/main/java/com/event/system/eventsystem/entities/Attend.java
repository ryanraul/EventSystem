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
   private Double balance;

   @OneToMany(mappedBy = "attend", cascade = CascadeType.ALL)
   private List<Ticket> tickets = new ArrayList<>();

   public List<Ticket> getTickets() {
      return tickets;
   }

   public void addTicket(Ticket ticket) {
      this.tickets.add(ticket);
   }

   public void removeTicket(Ticket ticket) {
      this.tickets.remove(ticket);
   }


   public Attend() {

   }

   public Attend(Double balance) {
      this.balance = balance;
   }

   public Attend(String name, String email, Double balance) {
      super(name, email);
      this.balance = balance;
   }

   public Attend(AttendDTOInsert attendeeDTOInsert){
      super(attendeeDTOInsert.getName(), attendeeDTOInsert.getEmail());
   }

   public Double getBalance() {
      return balance;
   }

   public void setBalance(Double balance) {
      this.balance = balance;
   }

   public void setAttendToUpdate(AttendDTOUpdate attendDTOUpdate) {
      this.setEmail(attendDTOUpdate.getEmail());
   }
  
   
}
