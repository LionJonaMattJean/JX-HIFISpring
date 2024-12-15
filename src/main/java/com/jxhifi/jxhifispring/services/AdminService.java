package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Admin;
import com.jxhifi.jxhifispring.repositories.AdminRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
