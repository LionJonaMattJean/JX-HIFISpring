package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
}
