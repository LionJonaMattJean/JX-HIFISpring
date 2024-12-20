package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM `order` ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<Order> findTopByIdNumericPart();

    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Order findOrderById(@Param("id") String id);

    @Query("SELECT order FROM Order order WHERE order.customer.id =:id")
    List<Order> getAllOrdersByCustomerId(@Param("id") String id);

}
