package com.event.system.eventsystem.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.AttendDTO.AttendDTO;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOInsert;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOUpdate;
import com.event.system.eventsystem.entities.Attend;
import com.event.system.eventsystem.repositories.AttendRepository;
import com.event.system.eventsystem.repositories.UserRepository;
import com.event.system.eventsystem.utils.ValidationResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttendService {
   @Autowired
   private AttendRepository attendRepository;

   @Autowired
   private UserRepository userRepository;

   public Page<AttendDTO> getAttendees(PageRequest pageRequest, String name, String email){
		try {
         Page<Attend> attendees = attendRepository.find(pageRequest, name, email);
         return attendees.map( a -> new AttendDTO(a));
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   public AttendDTO getAttendById(Long id) {
   Optional<Attend> attendOptional = attendRepository.findById(id);
   Attend attend = attendOptional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found."));

   return new AttendDTO(attend);
   }

   public AttendDTO insertAttend(AttendDTOInsert attendDTOInsert) {
      try {
         Attend attend = new Attend(attendDTOInsert);

         var validationResult = emailValidate(attend.getEmail());

         if(!validationResult.IsValid())
            throw new Exception(validationResult.errors.get(0).message);

         attend = attendRepository.save(attend);
         return new AttendDTO(attend);
      }catch (Exception e){
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   private ValidationResult emailValidate(String email) {
      try {
         ValidationResult validationResult = new ValidationResult();
         var users = userRepository.findAll();
         var emailInUse = users.stream().filter(u -> u.getEmail().equals(email)).count() != 0;
         
         if(emailInUse)
            validationResult.setErrors("Error: Email is already in use");
         
         return validationResult;     
      } catch (Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
   }

   public AttendDTO updateAttend(Long id, AttendDTOUpdate attendDTOUpdate) {
      try {
			Attend attend = attendRepository.getOne(id);
			attend.setAttendToUpdate(attendDTOUpdate);

			attend = attendRepository.save(attend);
			return new AttendDTO(attend);

      } catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found");
      } catch (Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
   }

	public void deleteAttend(Long id){
		try {
			attendRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found");
      } catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
