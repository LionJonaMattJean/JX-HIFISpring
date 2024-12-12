package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.OrderItem;
import com.jxhifi.jxhifispring.repositories.OrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    private OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    private OrderItem getOrderItem(String id) {
        return orderItemRepository.findOrderItemById(id);
    }

    private int getOrderItemQuantity(String id){
        return orderItemRepository.getQuantity(id);
    }

    private void updateOrderItemQuantity(String id, int newQuantity) {
        orderItemRepository.updateQuantity(id, newQuantity);
    }

    public void deleteOrderItem(String id) {
        int converted = Integer.parseInt(id);
        orderItemRepository.deleteById(converted);
    }
}
