package com.event.system.eventsystem.dto;

import com.event.system.eventsystem.entities.Event;

public class EventDTO {
   private Long id;
   private String name;
   private String place;
   
   public EventDTO (){

   }


   public EventDTO (Event event){
      this.id = event.getId();
      this.name = event.getName();
      this.place = event.getPlace();
   }

   public EventDTO(Long id, String name, String place) {
      this.id = id;
      this.name = name;
      this.place = place;
   }
      
   public String getPlace() {
      return place;
   }

   public void setPlace(String place) {
      this.place = place;
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
