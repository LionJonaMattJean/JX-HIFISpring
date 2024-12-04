package com.jxhifi.jxhifispring.repositories;


import com.jxhifi.jxhifispring.entities.SpecificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface SpecificationDetailsRepository extends JpaRepository<SpecificationDetails, String> {
    Optional<SpecificationDetails> findTopByOrderByIdDesc();

    List<SpecificationDetails> findByProduct_Id(String id);
}