package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CategoryRepository extends JpaRepository<Category, String> {
  Optional<Category> findTopByOrderByIdDesc();
}