package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query(value = "select * from OrderItem ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    Optional<OrderItem> findFirstByOrderById();



    //-FIND QUERIES--------------------------
    @Query("select oItem from OrderItem oItem where oItem.id = :id")
     OrderItem findOrderItemById(@Param("id") String id);

    @Query("select oItem.quantity from OrderItem  oItem where oItem.id = :id")
     int getQuantity(@Param("id") String id);

    @Query("select oItem from OrderItem oItem where oItem.order.id = :id")
     List<OrderItem> findOrderItemByOrderId(@Param("id") String id);


    @Modifying
    @Query("update OrderItem item set item.quantity = ?2 where item.id = ?1")
    void updateQuantity(@Param("id") String id, int newQuantity);

    /*
    //---Shopping Cart Service methods
    List<OrderItem> findBycustomerId(String customerId);

    OrderItem findBycustomerIdAndProductId(String customerId, String productId);

    void deleteBycustomerIdAndProductId(String customerId, String productId);

    */
}
