package com.event.system.eventsystem.repositories;

import org.springframework.stereotype.Repository;

import com.event.system.eventsystem.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface EventRepository extends JpaRepository <Event, Long> {

   @Query("SELECT e FROM Event e "
         +"WHERE "
            +"(LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')))  "      
            +"AND (LOWER(e.place) LIKE LOWER(CONCAT('%', :place, '%'))) "      
   )
   public Page <Event> find(Pageable pageReaquest, String name, String place);

}
