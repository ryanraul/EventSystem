package com.event.system.eventsystem.dto.PlaceDTO;

import javax.validation.constraints.NotBlank;

import com.event.system.eventsystem.entities.Place;

public class PlaceDTOUpdate {
   @NotBlank(message = "The place 'name' cannot be empty!")
   private String name;

   @NotBlank(message = "The place 'address' cannot be empty!")
   private String address;
   
   public PlaceDTOUpdate() {
      
   }

   public PlaceDTOUpdate(Place place) {
      this.name = place.getName();
      this.address = place.getAddress();
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }
}
