package com.event.system.eventsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.AttendeeDTO.AttendeeDTO;
import com.event.system.eventsystem.dto.AttendeeDTO.AttendeeDTOInsert;
import com.event.system.eventsystem.dto.AttendeeDTO.AttendeeDTOUpdate;
import com.event.system.eventsystem.entities.Attendee;
import com.event.system.eventsystem.repositories.AttendeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttendeeService {
   @Autowired
   private AttendeeRepository attendeeRepository;

   public List<AttendeeDTO> getAttendees(){
		List<Attendee> attendees = attendeeRepository.findAll();
      var attendeesDTO = toDTOList(attendees);
      return attendeesDTO;
   }

   private List<AttendeeDTO> toDTOList(List<Attendee> attendees) {
      List <AttendeeDTO> attendeesDTO = new ArrayList<>();

      for (Attendee attendee: attendees){
         AttendeeDTO attendeeDTO = new AttendeeDTO(attendee);
         attendeesDTO.add(attendeeDTO);
      }

      return attendeesDTO;
   }

    public AttendeeDTO getAttendeeById(Long id) {
      Optional<Attendee> attendeeOptional = attendeeRepository.findById(id);
      Attendee attendee = attendeeOptional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found."));

      return new AttendeeDTO(attendee);
    }

    public AttendeeDTO insertAttendee(AttendeeDTOInsert attendeeDTOInsert) {
        try {
            Attendee attendee = new Attendee(attendeeDTOInsert);
            attendee = attendeeRepository.save(attendee);
            return new AttendeeDTO(attendee);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

   public AttendeeDTO updateAttendee(Long id, AttendeeDTOUpdate attendeeDTOUpdate) {
      try {
			Attendee attendee = attendeeRepository.getOne(id);
			attendee.setAttendeeToUpdate(attendeeDTOUpdate);

			var validation = attendee.validate();
			if(!validation.IsValid())
			throw new Exception(validation.errors.get(0).message);
			attendee = attendeeRepository.save(attendee);
			return new AttendeeDTO(attendee);

      } catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found");
      } catch (Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
   }

	public void deleteAttendee(Long id){
		try {
			attendeeRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found");
      } catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
