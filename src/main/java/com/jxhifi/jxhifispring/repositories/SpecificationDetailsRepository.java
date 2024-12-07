package com.jxhifi.jxhifispring.repositories;



import com.jxhifi.jxhifispring.entities.SpecificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
public interface SpecificationDetailsRepository extends JpaRepository<SpecificationDetails, String> {
    @Query(value = "SELECT * FROM specification_details ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<SpecificationDetails> findTopByIdNumericPart();

    List<SpecificationDetails> findByProduct_Id(String id);
}