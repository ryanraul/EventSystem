package com.event.system.eventsystem.dto;

import java.util.List;

import com.event.system.eventsystem.entities.Event;
import com.event.system.eventsystem.entities.Place;

public class EventDTO {
   private Long id;
   private String name;
   private List<Place> places;
   private Long amountFreeTickets;
   private Long amountPayedTickets;
   private Double priceTicket;
      
   public EventDTO (){

   }

   public EventDTO (Event event){
      this.id = event.getId();
      this.name = event.getName();
      this.places = event.getPlaces();
      this.amountFreeTickets = event.getAmountFreeTickets();
      this.amountPayedTickets = event.getAmountPayedTickets();
      this.priceTicket = event.getPriceTicket();
   }

   public EventDTO(Long id, String name, List<Place> places, Long amountFreeTickets,Long amountPayedTickets, Double priceTicket, Long adminId) {
      this.id = id;
      this.name = name;
      this.places = places;
      this.priceTicket = priceTicket;
      this.amountFreeTickets = amountFreeTickets;
      this.amountPayedTickets = amountPayedTickets;
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

   public List<Place> getPlaces() {
      return places;
   }

   public void addPlace(Place place) {
      this.places.add(place);
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }

}
