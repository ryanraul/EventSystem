package com.event.system.eventsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTO;
import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTOInsert;
import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTOUpdate;
import com.event.system.eventsystem.entities.Place;
import com.event.system.eventsystem.repositories.PlaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlaceService {
   @Autowired
   private PlaceRepository placeRepository;

   public List<PlaceDTO> getPlaces(){
      List<Place> places = placeRepository.findAll();
      var placesDTO = toDTOList(places);
      return placesDTO;
   }

   private List<PlaceDTO> toDTOList(List<Place> places) {
      List <PlaceDTO> placesDTO = new ArrayList<>();

      for (Place place: places){
         PlaceDTO placeDTO = new PlaceDTO(place);
         placesDTO.add(placeDTO);
      }

      return placesDTO;
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

         // var validation = place.validate();
         // if(!validation.IsValid())
         //    throw new Exception(validation.errors.get(0).message);

         place = placeRepository.save(place);
         return new PlaceDTO(place);

      } catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
      }catch (Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
   }

   public void deletePlace(Long id){
      try {
         placeRepository.deleteById(id);
      } catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
      } catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
   }


}
