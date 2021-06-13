package com.event.system.eventsystem.dto.TicketDTO;

import com.event.system.eventsystem.entities.TicketType;

public class AttendTicketDTO {
   private String attendName;
   private TicketType ticketType;
   
   public AttendTicketDTO(String attendName, TicketType ticketType) {
      this.attendName = attendName;
      this.ticketType = ticketType;
   }
   
   public String getAttendName() {
      return attendName;
   }
   public TicketType getTicketType() {
      return ticketType;
   }
}
