package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.repositories.AdminRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepositery adminRepositery;
}
