package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
  Optional<Product> findTopByOrderByIdDesc();

  Optional<List<Product>> findByCategory_Id(String categoryId);

  Optional<List<Product>> findByOnSale(boolean onSale);
}