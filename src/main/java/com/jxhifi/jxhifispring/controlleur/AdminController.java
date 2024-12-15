package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.DTO.ConvertAddressDTO_To_Address;
import com.jxhifi.jxhifispring.DTO.customer.AddressDTO;
import com.jxhifi.jxhifispring.DTO.customer.AdminDTO;
import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Admin;
import com.jxhifi.jxhifispring.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController extends ConvertAddressDTO_To_Address {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/new")
    public ResponseEntity<Admin> createCustomer(@RequestBody AdminDTO adminDTO) {

        Admin admin = new Admin();

        admin.setId(adminService.generateNewId());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setPhone(adminDTO.getPhone());
        admin.setRole("admin");
        admin.setDeleted(false);
        admin.setAddress(addressDTOToAddress(adminDTO.getAddress()));

        adminService.createNewAdmin(admin);

        return ResponseEntity.ok(admin);
    }
}
