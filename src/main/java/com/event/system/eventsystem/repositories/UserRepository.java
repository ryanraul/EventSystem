package com.event.system.eventsystem.repositories;

import com.event.system.eventsystem.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
}
