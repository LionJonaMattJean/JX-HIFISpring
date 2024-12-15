package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.DTO.customer.CustomerDTO;
import com.jxhifi.jxhifispring.DTO.customer.NewAccountCustomerDTO;
import com.jxhifi.jxhifispring.entities.Customer;
import com.jxhifi.jxhifispring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("/customer/new")
    public ResponseEntity<Customer> createCustomer(@RequestBody NewAccountCustomerDTO newCustomerDTO) {

        Customer customer = new Customer();

        customer.setId(customerService.generateNewId());
        customer.setFirstName(newCustomerDTO.getFirstName());
        customer.setEmail(newCustomerDTO.getEmail());
        customer.setPassword(newCustomerDTO.getPassword());
        customer.setRole("customer");
        customer.setDeleted(false);

        customerService.createNewAccountCustomer(customer);

        return ResponseEntity.ok(customer);
    }
}
