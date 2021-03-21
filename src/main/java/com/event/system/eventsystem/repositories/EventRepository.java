package com.event.system.eventsystem.repositories;

import org.springframework.stereotype.Repository;

import com.event.system.eventsystem.entities.Event;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
   
}
