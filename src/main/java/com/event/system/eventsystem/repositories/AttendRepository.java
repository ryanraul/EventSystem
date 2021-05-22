package com.event.system.eventsystem.repositories;

import com.event.system.eventsystem.entities.Attend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Long>{

   @Query
      (
         "SELECT a FROM Attend a "
         + "WHERE "
            +"(LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))) "
            +"AND (LOWER(a.email) LIKE LOWER(CONCAT('%', :email, '%'))) " 
      )
   public Page<Attend> find(Pageable pageRequest, String name, String email);
   
}

