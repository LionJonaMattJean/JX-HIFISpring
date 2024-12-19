package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM `order` ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<Order> findTopByIdNumericPart();


//    @Query(value = "select * FROM Order order by  CAST(SUBSTRING(id, 4) AS UNSIGNED)", nativeQuery = true)
//    List<Order> findAllOrdersByIdNumericPart();


    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Order findOrderById(@Param("id") String id);

//    @Query("SELECT order.customer.id FROM Order order WHERE order.id =: id")
//    String findIdCustomerByOrder(@Param("id") String id);
//
//    @Query("SELECT order.card FROM Order order WHERE order.id =:id")
//    Card findCardByOrderId(@Param("id") String orderId);
//
//    @Query("SELECT order.orderItems FROM Order order WHERE order.id =: id")
//    List<OrderItem> getOrderItemsByOrderId(@Param("id") String id);

    @Query("SELECT order FROM Order order WHERE order.customer.id =:id")
    List<Order> getAllOrdersByCustomerId(@Param("id") String id);


    //TBD si je cree des get et update pour le reste, je ne vois pas l'utilitee

//    @Modifying
//    @Query("update Order order set order.status =?2 WHERE order.id =?1")
//    void updateOrderStatus(@Param("id") String id, @Param("status") String status);
//
//    @Modifying
//    @Query("update Order order set order.shippingAddress =?2 WHERE order.id =?1")
//    void updateShippingAddress(@Param("id") String id, @Param("shippingAddress") Address newAddress);


}
