package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
@RepositoryRestResource
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Query(value = "SELECT * FROM review ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<Review> findTopByIdNumericPart();
}