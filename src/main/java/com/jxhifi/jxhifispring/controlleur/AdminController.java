package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
}
