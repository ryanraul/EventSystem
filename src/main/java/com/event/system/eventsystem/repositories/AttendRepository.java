package com.event.system.eventsystem.repositories;

import com.event.system.eventsystem.entities.Attend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Long>{
   
}

