package com.event.system.eventsystem.repositories;

import com.event.system.eventsystem.entities.Attendee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long>{
   
}

