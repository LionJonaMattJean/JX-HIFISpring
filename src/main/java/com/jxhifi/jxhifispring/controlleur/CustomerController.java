package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.DTO.customer.CustomerDTO;
import com.jxhifi.jxhifispring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customersDTO = customerService.getAllCustomers();
        return ResponseEntity.ok(customersDTO);
    }
}
