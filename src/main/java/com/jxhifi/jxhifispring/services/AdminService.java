package com.jxhifi.jxhifispring.services;

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

    public void updateAdmin(Admin admin) {
        adminRepositery.save(admin);
    }
}
