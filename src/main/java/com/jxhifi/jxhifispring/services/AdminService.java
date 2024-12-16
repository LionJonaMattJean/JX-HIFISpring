package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.DTO.customer.AddressDTO;
import com.jxhifi.jxhifispring.DTO.customer.AdminDTO;
import com.jxhifi.jxhifispring.entities.Admin;
import com.jxhifi.jxhifispring.repositories.AdminRepositery;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepositery adminRepositery;
    private static int idNumber = 700;
    @PostConstruct
    public void init() {
        Optional<Admin> optionalAdmin = adminRepositery.findTopByIdNumericPart();
        if (optionalAdmin.isPresent()) {
            String lastId = optionalAdmin.get().getId();
            idNumber = Integer.parseInt(lastId.substring(3));
        }
    }
    public void createNewAdmin(Admin admin) {
        adminRepositery.save(admin);
    }
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepositery.findAll();
        return admins.stream().map(this::ToDTO).toList();
    }
    public AdminDTO ToDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPhone(admin.getPhone());

        if(admin.getAddress()!=null){
            AddressDTO addressDTO = new AddressDTO();

            addressDTO.setAddress(admin.getAddress().getAddress());
            addressDTO.setCity(admin.getAddress().getCity());
            addressDTO.setProvince(admin.getAddress().getProvince());
            addressDTO.setPostalCode(admin.getAddress().getPostalCode());
            addressDTO.setCountry(admin.getAddress().getCountry());

            adminDTO.setAddress(addressDTO);
        }
        return adminDTO;
    }

    public String generateNewId() {
        idNumber=idNumber+51;
        return "ADM" + idNumber;
    }

    public List<Admin> getAdmins() {
        return adminRepositery.findAll();
    }
    public Optional<Admin>getAdminById(String id){
        return adminRepositery.findById(id);
    }
    public AdminDTO getAdminByIdDTO(String id) {

        Optional<Admin> admin = adminRepositery.findById(id);
        return admin.map(this::ToDTO).orElse(null);
    }
    public void updateAdmin(Admin admin) {
        adminRepositery.save(admin);
    }

    public void deleteAdmin(Admin admin) {
        adminRepositery.delete(admin);
    }
}
