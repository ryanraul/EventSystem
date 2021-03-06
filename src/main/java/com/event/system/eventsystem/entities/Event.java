package com.event.system.eventsystem.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.event.system.eventsystem.dto.EventDTOInsert;
import com.event.system.eventsystem.dto.EventDTOUpdate;
import com.event.system.eventsystem.utils.ValidationResult;

@Entity
@Table(name="TB_EVENT")
public class Event implements Serializable{

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String description;
   private LocalDate startDate;
   private LocalDate endDate;
   private LocalTime startTime;
   private LocalTime endTime;
   private String emailContact;
   private Long amountFreeTickets;
   private Long amountPayedTickets;
   private Long freeTickectsSelled;
   private Long payedTickectsSelled;
   private Double priceTicket;

   @ManyToOne
   @JoinColumn(name = "ADMIN_USER_ID")
   private Admin admin;
   
   @ManyToMany
   @JoinTable(
      name="TB_EVENT_PLACE",
      joinColumns =  @JoinColumn(name="EVENT_ID"),
      inverseJoinColumns = @JoinColumn(name="PLACE_ID")
   )
   private List<Place> places = new ArrayList<>();

   @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
   private List<Ticket> tickets = new ArrayList<>();

   public ValidationResult ValidateUpdate(){
      ValidationResult validationResult = new ValidationResult();

      if(this.endDate.isBefore(LocalDate.now())){
         validationResult.setErrors("Error: The event cannot be changed after its end date.");
         return validationResult;
      }
      
      return validationResult;
   }

   public ValidationResult DatesAndTimesValidate() {
      ValidationResult validationResult = new ValidationResult();
   
      if(this.startDate.isBefore(LocalDate.now())){
         validationResult.setErrors("Error: Start date must be after or equal to today!");
         return validationResult;
      }

      if(this.startDate.isAfter(this.endDate)){
         validationResult.setErrors("Error: Start date is after end date!");
         return validationResult;
      }

      if(this.startDate.isEqual(this.endDate) && this.startTime.isAfter(endTime)){
         validationResult.setErrors("Error: Start time must be before end time!");
         return validationResult;         
      }
      
      return validationResult;
   }
   

   public Event(){

   }
   
   public Event(Long id, String name, String description, LocalDate startDate, LocalDate endDate, LocalTime startTime,
         LocalTime endTime, String emailContact, Admin admin) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.startDate = startDate;
      this.endDate = endDate;
      this.startTime = startTime;
      this.endTime = endTime;
      this.emailContact = emailContact;
      this.admin = admin;
   }

   public Event(EventDTOInsert eventDTO) {
      this.name = eventDTO.getName();
      this.description = eventDTO.getDescription();
      this.startDate = eventDTO.getStartDate();
      this.endDate = eventDTO.getEndDate();
      this.startTime = eventDTO.getStartTime();
      this.endTime = eventDTO.getEndTime();
      this.emailContact = eventDTO.getEmailContact();
      this.amountFreeTickets = eventDTO.getAmountFreeTickets();
      this.amountPayedTickets = eventDTO.getAmountPayedTickets();
      this.freeTickectsSelled = 0L;
      this.payedTickectsSelled = 0L;
      this.priceTicket = eventDTO.getPriceTicket();
   }    
  

   public Long getFreeTickectsSelled() {
      return freeTickectsSelled;
   }

   public void setFreeTickectsSelled(Long freeTickectsSelled) {
      this.freeTickectsSelled = freeTickectsSelled;
   }

   public Long getPayedTickectsSelled() {
      return payedTickectsSelled;
   }

   public void setPayedTickectsSelled(Long payedTickectsSelled) {
      this.payedTickectsSelled = payedTickectsSelled;
   }

   public void addTicket(Ticket ticket) {
      this.tickets.add(ticket);
   }

   public void removeTicket(Ticket ticket) {
      this.tickets.remove(ticket);
   }

   public Admin getAdmin() {
      return admin;
   }

   public void setAdmin(Admin admin) {
      this.admin = admin;
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

   public List<Ticket> getTickets() {
      return tickets;
   }
   
   public List<Place> getPlaces() {
      return places;
   }

   public void addPlace(Place place) {
      this.places.add(place);
   }

   public void removePlace(Place place) {
      this.places.remove(place);
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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

   public String getEmailContact() {
      return emailContact;
   }

   public void setEmailContact(String emailContact) {
      this.emailContact = emailContact;
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
      Event other = (Event) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      return true;
   }

   public void setEventToUpdate(EventDTOUpdate eventDTOUpdate) {
         this.description = eventDTOUpdate.getDescription();
         this.startDate = eventDTOUpdate.getStartDate();
         this.endDate = eventDTOUpdate.getEndDate();
         this.startTime = eventDTOUpdate.getStartTime();
         this.endTime = eventDTOUpdate.getEndTime();
         this.amountFreeTickets = eventDTOUpdate.getAmountFreeTickets();
         this.amountPayedTickets = eventDTOUpdate.getAmountPayedTickets();
         this.priceTicket = eventDTOUpdate.getPriceTicket();
   }



}
