package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.OrderItem;
import com.jxhifi.jxhifispring.repositories.OrderItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final EntityManager entityManager;

    public OrderItemService(OrderItemRepository orderItemRepository, EntityManager entityManager) {
        this.orderItemRepository = orderItemRepository;
        this.entityManager = entityManager;
    }

    private OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    private OrderItem getOrderItem(String id) {
        return orderItemRepository.findOrderItemById(id);
    }

    private int getOrderItemQuantity(String id){
        return orderItemRepository.getQuantity(id);
    }

    private List<OrderItem> getAllItemsByOrderId(String id){
        return orderItemRepository.findOrderItemByOrderId(id);
    }





    private void updateOrderItem(OrderItem orderItem) {
        OrderItem managedOrderItem = entityManager.merge(orderItem);
        orderItemRepository.save(managedOrderItem);
    }

    private void updateOrderItemQuantity(String id, int newQuantity) {
        orderItemRepository.updateQuantity(id, newQuantity);
    }

    private void deleteOrderItem(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
    }

    public void deleteOrderItem(String id) {
        int converted = Integer.parseInt(id);
        orderItemRepository.deleteById(converted);
    }
}
