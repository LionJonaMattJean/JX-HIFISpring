package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.DTO.customer.AddressDTO;
import com.jxhifi.jxhifispring.DTO.customer.CustomerDTO;
import com.jxhifi.jxhifispring.entities.Customer;
import com.jxhifi.jxhifispring.repositories.CustomerRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepositery customerRepositery;
    private static int idNumber = 2000;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepositery.findAll();
        return customers.stream() .map(this::ToDTO).collect(Collectors.toList()); // Conversion du customer
    }

    public CustomerDTO getCustomerById(String id) {
        Customer customer = customerRepositery.findCustomerById(id);
        return ToDTO(customer);
    }
    public Customer getCustomer(String id) {
        return customerRepositery.findCustomerById(id);
    }

    public void createNewCustomer(Customer customer) {
        customerRepositery.save(customer);
    }

    //Converti chaque Customer en ObjetDTO pour faciliter le transfert de data.
    public CustomerDTO ToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setRole(customer.getRole());
        customerDTO.setDeleted(customer.isDeleted());

        // Convertir l'adresse si elle existe
        if (customer.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();

            addressDTO.setAddress(customer.getAddress().getAddress());
            addressDTO.setCity(customer.getAddress().getCity());
            addressDTO.setProvince(customer.getAddress().getProvince());
            addressDTO.setPostalCode(customer.getAddress().getPostalCode());
            addressDTO.setCountry(customer.getAddress().getCountry());

            customerDTO.setAddress(addressDTO);
        }
        return customerDTO;
    }
    public void updateCustomer(Customer customer) {
        customerRepositery.save(customer);
    }
    public void deleteCustomer(Customer customer) {
        customerRepositery.delete(customer);
    }

    public String generateNewId() {
        return "USE" + (idNumber+37);
    }
}