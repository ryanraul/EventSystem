package com.event.system.eventsystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDTOUpdate {
   
   private String description;
   private String place;
   private LocalDate startDate;
   private LocalDate endDate;
   private LocalTime startTime;
   private LocalTime endTime;

   public void setPlace(String place) {
      this.place = place;
   }

   public EventDTOUpdate(String description, String place, LocalDate startDate, LocalDate endDate, LocalTime startTime,
   LocalTime endTime) {
      this.description = description;
      this.startDate = startDate;
      this.endDate = endDate;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   public EventDTOUpdate(){

   }

   public String getPlace() {
      return place;
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
