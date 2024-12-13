package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select order from Order order where order.id =: id")
    public Order findOrderById(@Param("id") String id);

    @Query("select order.idCustomer from Order order where order.id =: id")
    public String findIdCustomerByOrder(@Param("id") String id);

    @Query("select order.card from Order order where order.id =:id")
    public Card findCardByOrderId(@Param("id")String orderId);

    @Query("select order.orderItems from Order order where order.id =: id")
    public List<OrderItem> getOrderItemsByOrderId(@Param("id") String id);

    @Query("select order from Order order where order.idCustomer =:id")
    public List<Order> getAllOrdersByCustomerId(@Param("id") String id);




    //TBD si je cree des get et update pour le reste, je ne vois pas l'utilitee

    @Modifying
    @Query("update Order order set order.status =?2 where order.id =?1")
    public void updateOrderStatus(@Param("id") String id, @Param("status") String status);

    @Modifying
    @Query("update Order order set order.shippingAddress =?2 where order.id =?1")
    public void updateShippingAddress(@Param("id") String id, @Param("shippingAddress") Address newAddress);












}
