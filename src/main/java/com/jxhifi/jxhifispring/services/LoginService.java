package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Customer;
import com.jxhifi.jxhifispring.repositories.CustomerRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private CustomerRepositery customerRepositery;

    public boolean validateCustomerCredentials(String email, String password) {
        Customer customer = customerRepositery.findByEmail(email);

        if (customer != null && customer.getPassword() != null) {
            // Comparaison en clair
            return password.equals(customer.getPassword());
        }
        return false;
    }





}
