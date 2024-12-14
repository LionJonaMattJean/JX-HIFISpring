package com.jxhifi.jxhifispring.repositories;


import com.jxhifi.jxhifispring.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query(value = "SELECT * FROM store ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<Store> findTopByIdNumericPart();

    Optional<Store> findById(String id);

}
