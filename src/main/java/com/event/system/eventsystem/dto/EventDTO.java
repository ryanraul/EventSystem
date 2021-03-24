package com.event.system.eventsystem.dto;

import com.event.system.eventsystem.entities.Event;

public class EventDTO {
   private Long id;
   private String name;
   
   public EventDTO (){

   }

   public EventDTO (Event event){
      this.id = event.getId();
      this.name = event.getName();
   }

   public EventDTO(Long id, String name) {
      this.id = id;
      this.name = name;
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
