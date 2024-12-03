package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Image;
import com.jxhifi.jxhifispring.entities.SpecificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpecificationDetailsRepository extends JpaRepository<SpecificationDetails, String> {
    Optional<SpecificationDetails> findTopByOrderByIdDesc();
}