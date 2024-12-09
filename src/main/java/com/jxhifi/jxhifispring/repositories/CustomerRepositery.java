package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface CustomerRepositery extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.id = ?1")
    Customer findCustomerById(String id);
}
