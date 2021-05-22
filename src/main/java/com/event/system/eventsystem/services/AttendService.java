package com.event.system.eventsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.AttendDTO.AttendDTO;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOInsert;
import com.event.system.eventsystem.dto.AttendDTO.AttendDTOUpdate;
import com.event.system.eventsystem.entities.Attend;
import com.event.system.eventsystem.repositories.AttendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttendService {
   @Autowired
   private AttendRepository attendRepository;

   public List<AttendDTO> getAttendees(){
		List<Attend> attendees = attendRepository.findAll();
      var attendeesDTO = toDTOList(attendees);
      return attendeesDTO;
   }

   private List<AttendDTO> toDTOList(List<Attend> attendees) {
      List <AttendDTO> attendeesDTO = new ArrayList<>();

      for (Attend attend: attendees){
         AttendDTO attendDTO = new AttendDTO(attend);
         attendeesDTO.add(attendDTO);
      }

      return attendeesDTO;
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
