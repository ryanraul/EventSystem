package com.event.system.eventsystem.dto;

import java.util.List;

import com.event.system.eventsystem.entities.Event;
import com.event.system.eventsystem.entities.Place;

public class EventDTO {
   private Long id;
   private String name;
   private List<Place> places;
   
   public EventDTO (){

   }

   public EventDTO (Event event){
      this.id = event.getId();
      this.name = event.getName();
      this.places = event.getPlaces();
   }

   public EventDTO(Long id, String name, List<Place> places) {
      this.id = id;
      this.name = name;
      this.places = places;
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
