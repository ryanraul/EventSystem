package com.event.system.eventsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.AdminDTO.AdminDTO;
import com.event.system.eventsystem.dto.AdminDTO.AdminDTOInsert;
import com.event.system.eventsystem.dto.AdminDTO.AdminDTOUpdate;
import com.event.system.eventsystem.entities.Admin;
import com.event.system.eventsystem.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {
   @Autowired
   private AdminRepository adminRepository;

   public List<AdminDTO> getAdmins(){
		List<Admin> admins = adminRepository.findAll();
      var adminsDTO = toDTOList(admins);
      return adminsDTO;
   }

   private List<AdminDTO> toDTOList(List<Admin> admins) {
      List <AdminDTO> adminsDTO = new ArrayList<>();

      for (Admin admin: admins){
         AdminDTO adminDTO = new AdminDTO(admin);
         adminsDTO.add(adminDTO);
      }

      return adminsDTO;
   }

    public AdminDTO getAdminById(Long id) {
      Optional<Admin> adminOptional = adminRepository.findById(id);
      Admin admin = adminOptional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found."));

      return new AdminDTO(admin);
    }

    public AdminDTO insertAdmin(AdminDTOInsert adminDTOInsert) {
        try {
            Admin admin = new Admin(adminDTOInsert);
            admin = adminRepository.save(admin);
            return new AdminDTO(admin);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

   public AdminDTO updateAdmin(Long id, AdminDTOUpdate adminDTOUpdate) {
      try {
			Admin admin = adminRepository.getOne(id);
			admin.setAdminToUpdate(adminDTOUpdate);

			var validation = admin.validate();
			if(!validation.IsValid())
			throw new Exception(validation.errors.get(0).message);
			admin = adminRepository.save(admin);
			return new AdminDTO(admin);

      } catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
      } catch (Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
   }

	public void deleteAdmin(Long id){
		try {
			adminRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
      } catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
