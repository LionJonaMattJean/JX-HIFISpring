package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {
    @Query(value = "SELECT * FROM image ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<Image> findTopByIdNumericPart();
}