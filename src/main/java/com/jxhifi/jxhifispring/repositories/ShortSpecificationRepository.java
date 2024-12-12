package com.jxhifi.jxhifispring.repositories;



import com.jxhifi.jxhifispring.entities.ShortSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;
import java.util.Optional;
@RepositoryRestResource
public interface ShortSpecificationRepository extends JpaRepository<ShortSpecification, String> {
    @Query(value = "SELECT * FROM short_specification ORDER BY CAST(SUBSTRING(id, 5) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<ShortSpecification> findTopByIdNumericPart();

    /**
     * Finds a list of optional short specifications by the ID of the associated product.
     *
     * @param productId the ID of the product whose short specifications are to be retrieved
     * @return a list of optional short specifications associated with the given product ID
     ** Note: This is a JPA derived query, so no @Query annotation is needed.
     */
    List<ShortSpecification> findByProduct_Id(String productId);
}