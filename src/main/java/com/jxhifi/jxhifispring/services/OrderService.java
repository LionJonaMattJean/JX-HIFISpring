package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.entities.OrderItem;
import com.jxhifi.jxhifispring.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private OrderRepository orderRepository;

    private void addOrder(Order order){
        orderRepository.save(order);
    }

    private Order getOrderById(String id){
        return orderRepository.findOrderById(id);
    }

    private String getIdCustomerByOrderId(String id){
        return orderRepository.findIdCustomerByOrder(id);
    }

    private Card getCardByOrderId(String id){
        return orderRepository.findCardByOrderId(id);
    }

    private List<OrderItem> getOrderItemsByOrderId(String id){
        return orderRepository.getOrderItemsByOrderId(id);
    }

    private void updateShippingAddress(String id, Address address){
        orderRepository.updateShippingAddress(id, address);
    }

    private void updateOrderStatus(String id, String newStatus){
        orderRepository.updateOrderStatus(id, newStatus);
    }

    private void deleteOrder(String id){
        int converted = Integer.parseInt(id);
        orderRepository.deleteById(converted);
    }
}