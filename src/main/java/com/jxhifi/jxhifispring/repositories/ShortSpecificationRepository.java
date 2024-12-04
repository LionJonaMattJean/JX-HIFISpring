package com.jxhifi.jxhifispring.repositories;


import com.jxhifi.jxhifispring.entities.ShortSpecification;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
public interface ShortSpecificationRepository extends JpaRepository<ShortSpecification, String> {
    Optional<ShortSpecification> findTopByOrderByIdDesc();

    /**
     * Finds a list of optional short specifications by the ID of the associated product.
     *
     * @param productId the ID of the product whose short specifications are to be retrieved
     * @return a list of optional short specifications associated with the given product ID
     ** Note: This is a JPA derived query, so no @Query annotation is needed.
     */
    List<ShortSpecification> findByProduct_Id(String productId);
}