package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
  @Query(value = "SELECT * FROM category ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
  Optional<Category> findTopByIdNumericPart();
}