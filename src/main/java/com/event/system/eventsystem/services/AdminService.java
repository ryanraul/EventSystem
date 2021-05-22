package com.event.system.eventsystem.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.event.system.eventsystem.dto.AdminDTO.AdminDTO;
import com.event.system.eventsystem.dto.AdminDTO.AdminDTOInsert;
import com.event.system.eventsystem.dto.AdminDTO.AdminDTOUpdate;
import com.event.system.eventsystem.entities.Admin;
import com.event.system.eventsystem.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {
   @Autowired
   private AdminRepository adminRepository;

   public Page<AdminDTO> getAdmins(PageRequest pageRequest, String name, String email){
		try {
         Page<Admin> admins = adminRepository.find(pageRequest, name, email);
         return admins.map(a -> new AdminDTO(a));
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
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
