package com.event.system.eventsystem.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TICKET")
public class Ticket implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private TicketType type;
   private Instant date;
   private Double price;
      
   @ManyToOne()
   @JoinColumn(name = "EVENT_ID")
   private Event event;

   @ManyToOne()
   @JoinColumn(name = "USER_ID")
   private Attend attend;


   public Ticket() {
      
   }

   public Ticket(TicketType type, Instant date, Double price) {
      this.type = type;
      this.date = date;
      this.price = price;
   }

   public Ticket(TicketType type, Instant date, Double price, Event event, Attend attend) {
      this.type = type;
      this.date = date;
      this.price = price;
      this.event = event;
      this.attend = attend;
   }
   
   public Long getId() {
      return id;
   }
   public Attend getAttend() {
      return attend;
   }
   public TicketType getType() {
      return type;
   }
   public void setType(TicketType type) {
      this.type = type;
   }
   public Instant getDate() {
      return date;
   }
   public void setDate(Instant date) {
      this.date = date;
   }
   public Double getPrice() {
      return price;
   }
   public void setPrice(Double price) {
      this.price = price;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Ticket other = (Ticket) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      return true;
   }
}
