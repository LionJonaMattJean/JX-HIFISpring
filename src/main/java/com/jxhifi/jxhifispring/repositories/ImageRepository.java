package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ImageRepository extends JpaRepository<Image, String> {
    Optional<Image> findTopByOrderByIdDesc();
}