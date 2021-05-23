package com.event.system.eventsystem.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTO;
import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTOInsert;
import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTOUpdate;
import com.event.system.eventsystem.entities.Place;
import com.event.system.eventsystem.repositories.PlaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlaceService {
   @Autowired
   private PlaceRepository placeRepository;

   public Page<PlaceDTO> getPlaces(PageRequest pageRequest, String name, String address){
      try {
         Page<Place> places = placeRepository.find(pageRequest, name, address);
         return places.map(p -> new PlaceDTO(p));
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   public PlaceDTO getPlaceById(Long id){
      Optional<Place> placeOptional = placeRepository.findById(id);
      Place place = placeOptional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));
      return new PlaceDTO(place);
   }

   public PlaceDTO insertPlace(PlaceDTOInsert placeDTOInsert){
      try {
         Place place = new Place(placeDTOInsert);
         place = placeRepository.save(place);
         return new PlaceDTO(place);
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   public PlaceDTO updatePlace(Long id, PlaceDTOUpdate placeDTOUpdate){
      try {
         Place place = placeRepository.getOne(id);
         place.setPlaceToUpdate(placeDTOUpdate);

         place = placeRepository.save(place);
         return new PlaceDTO(place);

      } catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
      } catch (Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
   }

   public void deletePlace(Long id){
      try {
         placeRepository.deleteById(id);
      } catch (EmptyResultDataAccessException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
      } catch (DataIntegrityViolationException e){
         throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The place cannot be deleted after being used at the event.");
      }
   }


}
