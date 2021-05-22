package com.event.system.eventsystem.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.AttendDTO.AttendDTO;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOInsert;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOUpdate;
import com.event.system.eventsystem.entities.Attend;
import com.event.system.eventsystem.repositories.AttendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttendService {
   @Autowired
   private AttendRepository attendRepository;

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
         attend = attendRepository.save(attend);
         return new AttendDTO(attend);
      }catch (Exception e){
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   public AttendDTO updateAttend(Long id, AttendDTOUpdate attendDTOUpdate) {
      try {
			Attend attend = attendRepository.getOne(id);
			attend.setAttendToUpdate(attendDTOUpdate);

			var validation = attend.validate();
			if(!validation.IsValid())
			throw new Exception(validation.errors.get(0).message);
			attend = attendRepository.save(attend);
			return new AttendDTO(attend);

      } catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found");
      } catch (Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
   }

	public void deleteAttend(Long id){
		try {
			attendRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found");
      } catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
