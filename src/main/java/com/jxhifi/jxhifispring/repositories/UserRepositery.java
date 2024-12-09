package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface UserRepositery extends JpaRepository<User, Long> {
}
