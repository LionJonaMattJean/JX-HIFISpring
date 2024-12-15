package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Admin;
import com.jxhifi.jxhifispring.repositories.AdminRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepositery adminRepositery;
    private static int idNumber = 700;

    public void createNewAdmin(Admin admin) {
        adminRepositery.save(admin);
    }


    public String generateNewId() {
        return "ADM" + (idNumber+51);
    }

    public List<Admin> getAdmins() {
        return adminRepositery.findAll();
    }
    public Optional<Admin>getAdminById(String id){
        return adminRepositery.findById(id);
    }
}
