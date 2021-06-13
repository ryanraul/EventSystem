package com.event.system.eventsystem.dto;

import java.util.ArrayList;
import java.util.List;

import com.event.system.eventsystem.dto.TicketDTO.AttendTicketDTO;

public class EventTicketDTO {
   private List<AttendTicketDTO> attendTickets = new ArrayList<>(); 
   private Long amountFreeTickets;
   private Long amountPayedTickets;
   private Long alreadyPaidAmountTickets;
   private Long alreadyPaidAmountFreeTickets;

   public Long getAmountFreeTickets() {
      return amountFreeTickets;
   }
   public List<AttendTicketDTO> getAttendTickets() {
      return attendTickets;
   }
   public void setAttendTickets(List<AttendTicketDTO> attendTickets) {
      this.attendTickets = attendTickets;
   }
   public void setAmountFreeTickets(Long amountFreeTickets) {
      this.amountFreeTickets = amountFreeTickets;
   }
   public Long getAmountPayedTickets() {
      return amountPayedTickets;
   }
   public void setAmountPayedTickets(Long amountPayedTickets) {
      this.amountPayedTickets = amountPayedTickets;
   }
   public Long getAlreadyPaidAmountTickets() {
      return alreadyPaidAmountTickets;
   }
   public void setAlreadyPaidAmountTickets(Long alreadyPaidAmountTickets) {
      this.alreadyPaidAmountTickets = alreadyPaidAmountTickets;
   }
   public Long getAlreadyPaidAmountFreeTickets() {
      return alreadyPaidAmountFreeTickets;
   }
   public void setAlreadyPaidAmountFreeTickets(Long alreadyPaidAmountFreeTickets) {
      this.alreadyPaidAmountFreeTickets = alreadyPaidAmountFreeTickets;
   }


   
}
