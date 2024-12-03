package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ReviewRepository extends JpaRepository<Review, String> {
    Optional<Review> findTopByOrderByIdDesc();
}