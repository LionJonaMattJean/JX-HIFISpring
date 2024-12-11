package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Customer;
import com.jxhifi.jxhifispring.repositories.CustomerRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepositery customerRepositery;

    public List<Customer> getAllCustomers() {
        return customerRepositery.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepositery.findCustomerById(id);
    }
}
