package com.event.system.eventsystem.dto.PlaceDTO;

import com.event.system.eventsystem.entities.Place;

public class PlaceDTOInsert {
   private String name;
   private String address;
   
   public PlaceDTOInsert() {
      
   }

   public PlaceDTOInsert(Place place) {
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
