package com.event.system.eventsystem.dto;

import com.event.system.eventsystem.entities.TicketType;

public class AttendEventTicketDTO {
   private Long attendId;
   private TicketType ticketType;
   
   public Long getAttendId() {
      return attendId;
   }
   public void setAttendId(Long attendId) {
      this.attendId = attendId;
   }
   public TicketType getTicketType() {
      return ticketType;
   }
   public void setTicketType(TicketType ticketType) {
      this.ticketType = ticketType;
   }
}
