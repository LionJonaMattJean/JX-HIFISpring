package com.jxhifi.jxhifispring.repositories;


import com.jxhifi.jxhifispring.entities.ShortSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ShortSpecificationRepository extends JpaRepository<ShortSpecification, String> {
    Optional<ShortSpecification> findTopByOrderByIdDesc();
}