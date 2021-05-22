package com.event.system.eventsystem.controllers;

import java.net.URI;
import java.util.List;

import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTO;
import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTOInsert;
import com.event.system.eventsystem.dto.PlaceDTO.PlaceDTOUpdate;
import com.event.system.eventsystem.services.PlaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/places")
public class PlaceController {
   @Autowired
   private PlaceService placeService;

   @GetMapping
   public ResponseEntity<List<PlaceDTO>> getPlaces(){
      var places = placeService.getPlaces();
      return ResponseEntity.ok(places);
   }

   @GetMapping("{id}")
   public ResponseEntity<PlaceDTO> getPlaceById(@PathVariable Long id){
      var place = placeService.getPlaceById(id);
      return ResponseEntity.ok(place);
   }

   @PostMapping
   public ResponseEntity<PlaceDTO> insertPlace(@RequestBody PlaceDTOInsert placeDTOInsert){
      PlaceDTO placeDTO = placeService.insertPlace(placeDTOInsert);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(placeDTO.getId()).toUri();
      return ResponseEntity.created(uri).body(placeDTO);
   }

   @PutMapping("{id}")
   public ResponseEntity<PlaceDTO> updatePlace(@RequestBody PlaceDTOUpdate placeDTOUpdate, @PathVariable Long id){
      PlaceDTO placeDTO = placeService.updatePlace(id, placeDTOUpdate);
      return ResponseEntity.ok().body(placeDTO);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Void> deletePlace(@PathVariable Long id){
      placeService.deletePlace(id);
      return ResponseEntity.noContent().build();
   }

}
