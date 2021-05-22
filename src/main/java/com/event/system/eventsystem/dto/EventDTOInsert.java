package com.event.system.eventsystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.event.system.eventsystem.entities.Place;

public class EventDTOInsert {
   
   private String name;
   private String description;
   private List<Place> places;
   private LocalDate startDate;
   private LocalDate endDate;
   private LocalTime startTime;
   private LocalTime endTime;
   private String emailContact;
   private Long amountFreeTickets;
   private Long amountPayedTickets;
   private Double priceTicket;
   private Long adminId;

   public EventDTOInsert(){

   }

   public EventDTOInsert(String name, String description, List<Place> places, LocalDate startDate, LocalDate endDate, LocalTime startTime,
      LocalTime endTime, String emailContact, Long amountFreeTickets,Long amountPayedTickets, Double priceTicket, Long adminId) {
      this.name = name;
      this.description = description;
      this.places = places;
      this.startDate = startDate;
      this.endDate = endDate;
      this.startTime = startTime;
      this.endTime = endTime;
      this.emailContact = emailContact;
      this.amountFreeTickets = amountFreeTickets;
      this.amountPayedTickets = amountPayedTickets;
      this.priceTicket = priceTicket;
      this.adminId = adminId;
   }

   
   public Long getAdminId() {
      return adminId;
   }

   public void setAdminId(Long adminId) {
      this.adminId = adminId;
   }

   public List<Place> getPlaces() {
      return places;
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
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getEmailContact() {
      return emailContact;
   }
   public void setEmailContact(String emailContact) {
      this.emailContact = emailContact;
   }
   
}
