package com.event.system.eventsystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.event.system.eventsystem.entities.Place;

public class EventDTOUpdate {
   
   private String description;
   private List<Place> places;
   private LocalDate startDate;
   private LocalDate endDate;
   private LocalTime startTime;
   private LocalTime endTime;
   private Long amountFreeTickets;
   private Long amountPayedTickets;
   private Double priceTicket;
   
   public EventDTOUpdate(){

   }

   public EventDTOUpdate(String description, List<Place> place, LocalDate startDate, LocalDate endDate, LocalTime startTime,
   LocalTime endTime,Long amountFreeTickets,Long amountPayedTickets, Double priceTicket) {
      this.description = description;
      this.startDate = startDate;
      this.endDate = endDate;
      this.startTime = startTime;
      this.endTime = endTime;
      this.amountFreeTickets = amountFreeTickets;
      this.amountPayedTickets = amountPayedTickets;
      this.priceTicket = priceTicket;
   }

   
   public Long getAmountFreeTickets() {
      return amountFreeTickets;
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

   public Double getPriceTicket() {
      return priceTicket;
   }

   public void setPriceTicket(Double priceTicket) {
      this.priceTicket = priceTicket;
   }

   public void addPlace(Place place) {
      this.places.add(place);
   }

   public List<Place> getPlaces() {
      return places;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public LocalDate getStartDate() {
      return startDate;
   }

   public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
   }

   public LocalDate getEndDate() {
      return endDate;
   }

   public void setEndDate(LocalDate endDate) {
      this.endDate = endDate;
   }

   public LocalTime getStartTime() {
      return startTime;
   }

   public void setStartTime(LocalTime startTime) {
      this.startTime = startTime;
   }

   public LocalTime getEndTime() {
      return endTime;
   }

   public void setEndTime(LocalTime endTime) {
      this.endTime = endTime;
   }


}
