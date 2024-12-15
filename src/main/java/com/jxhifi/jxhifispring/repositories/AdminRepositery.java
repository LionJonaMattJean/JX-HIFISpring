package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface AdminRepositery extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(String id);
}
