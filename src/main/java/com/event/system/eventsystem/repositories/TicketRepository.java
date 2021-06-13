package com.event.system.eventsystem.repositories;

import com.event.system.eventsystem.entities.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
   
}
