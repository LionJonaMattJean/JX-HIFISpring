package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.entities.OrderItem;
import com.jxhifi.jxhifispring.repositories.OrderRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private static long idNumber = 1L;
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;


    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    /*@PostConstruct
    private void initNumber(){
        Optional<Order> lastOrderOptional = this.orderRepository.findTopByIdNumericPart();
        if(lastOrderOptional.isPresent()){
            String lastId = lastOrderOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3));
        }
    }*/

    public OrderService(OrderRepository orderRepository, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
    }

    public void addOrder(Order order){
        orderRepository.save(order);
    }

    public Order getOrderById(String id){
        return orderRepository.findOrderById(id);
    }

    public String getIdCustomerByOrderId(String id){
        return orderRepository.findIdCustomerByOrder(id);
    }

    public Card getCardByOrderId(String id){
        return orderRepository.findCardByOrderId(id);
    }

    public List<OrderItem> getOrderItemsByOrderId(String id){
        return orderRepository.getOrderItemsByOrderId(id);
    }

    public List<Order> getAllOrdersFromCustomerId(String id){
        return orderRepository.getAllOrdersByCustomerId(id);
    }

    public void updateShippingAddress(String id, Address address){
        orderRepository.updateShippingAddress(id, address);
    }

    public void updateOrderStatus(String id, String newStatus){
        orderRepository.updateOrderStatus(id, newStatus);
    }

    public void updateOrder(Order order){
        Order managedOrder = entityManager.merge(order);
        orderRepository.save(managedOrder);
    }

    public void deleteOrder(String id){
        int converted = Integer.parseInt(id);
        orderRepository.deleteById(converted);
    }

    public synchronized String generateNewId() {
        String id = "ORD"+idNumber;
        idNumber++;
        return id ;
    }
}
