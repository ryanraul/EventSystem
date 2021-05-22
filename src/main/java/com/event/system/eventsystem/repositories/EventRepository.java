package com.event.system.eventsystem.repositories;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import com.event.system.eventsystem.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface EventRepository extends JpaRepository <Event, Long> {

   @Query("SELECT e FROM Event e "
         +"WHERE "
            +"(LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) "      
            +"AND (e.startDate > :dateFilter)"
            +"AND (LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%'))) "  
   )
   public Page <Event> find(Pageable pageReaquest, String name, LocalDate dateFilter, String description);

}
