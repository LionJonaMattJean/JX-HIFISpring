package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface AddressRepositery extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM address ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<Address> findTopByIdNumericPart();

    Address findById(String id);
}
