package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
  Optional<Product> findTopByOrderByIdDesc();
}