package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.OrderItem;
import com.jxhifi.jxhifispring.repositories.OrderItemRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderItemService {
    private static long idNumber = 1L;

    private final OrderItemRepository orderItemRepository;
    private final EntityManager entityManager;

/*
    @PostConstruct
    private void initNumber(){
        Optional<OrderItem> lastOrderItemOptional = this.orderItemRepository.findFirstByOrderById();
        if(lastOrderItemOptional.isPresent()){
            String lastId = lastOrderItemOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3));
        }
    }*/

    public synchronized String generateNewId() {
        String id = "ORI"+idNumber;
        idNumber++;
        return id ;
    }

    public OrderItemService(OrderItemRepository orderItemRepository, EntityManager entityManager) {
        this.orderItemRepository = orderItemRepository;
        this.entityManager = entityManager;
    }

//    public OrderItem addOrderItem(OrderItem orderItem) {
//        return orderItemRepository.save(orderItem);
//    }
//
//    public OrderItem getOrderItembyId(String id) {
//        return orderItemRepository.findOrderItemById(id);
//    }
//
//    public int getOrderItemQuantity(String id){
//        return orderItemRepository.getQuantity(id);
//    }
//
//    public List<OrderItem> getAllItemsByOrderId(String id){
//        return orderItemRepository.findOrderItemByOrderId(id);
//    }
//
//
//
//
//
//    public void updateOrderItem(OrderItem orderItem) {
//        OrderItem managedOrderItem = entityManager.merge(orderItem);
//        orderItemRepository.save(managedOrderItem);
//    }
//
//    public void updateOrderItemQuantity(String id, int newQuantity) {
//        orderItemRepository.updateQuantity(id, newQuantity);
//    }
//
//    public void deleteOrderItem(OrderItem orderItem) {
//        orderItemRepository.delete(orderItem);
//    }
//
//    public void deleteOrderItem(String id) {
//        int converted = Integer.parseInt(id);
//        orderItemRepository.deleteById(converted);
//    }
}
