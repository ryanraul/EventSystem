package com.event.system.eventsystem.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTOInsert;
import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTOUpdate;

@Entity
@Table(name = "TB_PLACE")
public class Place implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String address;
   
   public Place() {
      
   }

   public Place(String name, String address) {
      this.name = name;
      this.address = address;
   }

   public Place(PlaceDTOInsert placeDTOInsert) {
      this.name = placeDTOInsert.getName();
      this.address = placeDTOInsert.getAddress();
   }

   public void setPlaceToUpdate(PlaceDTOUpdate placeDTOUpdate) {
      this.name = placeDTOUpdate.getName();
      this.address = placeDTOUpdate.getAddress();
   }

   public Long getId(){
      return id;
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

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Place other = (Place) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      return true;
   }  
}
