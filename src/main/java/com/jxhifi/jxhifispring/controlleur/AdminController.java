package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.DTO.ConvertAddressDTO_To_Address;

import com.jxhifi.jxhifispring.DTO.customer.AdminDTO;

import com.jxhifi.jxhifispring.entities.Admin;

import com.jxhifi.jxhifispring.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController extends ConvertAddressDTO_To_Address {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/new")
    public ResponseEntity<Admin> createAdmin(@RequestBody AdminDTO adminDTO) {

        Admin admin = new Admin();

        admin.setId(adminService.generateNewId());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setPhone(adminDTO.getPhone());
        admin.setRole("administrator");
        admin.setDeleted(false);
        admin.setAddress(addressDTOToAddress(adminDTO.getAddress()));

        adminService.createNewAdmin(admin);

        return ResponseEntity.ok(admin);
    }
    @GetMapping("/admin")
    public ResponseEntity<List<Admin>> getAllAdmin(){
        return ResponseEntity.ok(adminService.getAdmins());
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable String id){
        AdminDTO admin=adminService.getAdminByIdDTO(id);
       return ResponseEntity.ok(admin);
    }
    @PutMapping("/admin/update/{id}")
      public ResponseEntity<Admin> updateAdmin(@RequestBody AdminDTO updateAdminDTO, @PathVariable String id){
        Admin admin=adminService.getAdminById(id).orElseThrow(()->new RuntimeException("Admin not found"));
        if(admin!=null){
            admin.setLastName(updateAdminDTO.getLastName());
            admin.setFirstName(updateAdminDTO.getFirstName());
            admin.setEmail(updateAdminDTO.getEmail());
            admin.setPhone(updateAdminDTO.getPhone());
            admin.setAddress(addressDTOToAddress(updateAdminDTO.getAddress()));
            admin.setRole("administrator");
            admin.setDeleted(updateAdminDTO.isDeleted());
            if(updateAdminDTO.getPassword()!=null){
                admin.setPassword(updateAdminDTO.getPassword());
            }
            adminService.updateAdmin(admin);

        }
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteAdmin(@PathVariable String id) {
        Admin admin=adminService.getAdminById(id).orElseThrow(()->new RuntimeException("Admin not found"));
        if(admin!=null){
            adminService.deleteAdmin(admin);
        }
    }
    @PutMapping("admin/deactivate/{id}")
    public void deactivateCustomer(@PathVariable String id) {
        Admin admin=adminService.getAdminById(id).orElseThrow(()->new RuntimeException("Admin not found"));
        if(admin!=null){
            admin.setDeleted(true);
            adminService.updateAdmin(admin);
        }
    }

}
