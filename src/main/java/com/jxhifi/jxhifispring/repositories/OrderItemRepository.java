package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.OrderItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    //-FIND QUERRIES--------------------------
    @Query("select oItem from OrderItem oItem where oItem.id=: id")
    public OrderItem findOrderItemById(@Param("id") String id);

    @Query("select oItem.quantity from OrderItem  oItem where oItem.id=:id")
    public int getQuantity(@Param("id") String id);

    @Modifying
    @Query("update OrderItem item set item.quantity = ?2 where item.id = ?1")
    public void updateQuantity(@Param("id") String id, int newQuantity);



}
