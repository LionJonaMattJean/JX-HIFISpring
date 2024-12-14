package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.DTO.order.OrderDTO;
import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.services.CustomerService;
import com.jxhifi.jxhifispring.services.OrderItemService;
import com.jxhifi.jxhifispring.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    private final OrderService orderService;

    public OrderController(OrderItemService orderItemService, CustomerService customerService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    //tempCustomerid pourait etre a la fin du URL, TBD
    @GetMapping("/{tempCustomerId}/account-details/orders")
    public ResponseEntity<List<Order>> getOrdersFromCustomer(@PathVariable String tempCustomerId){
        return ResponseEntity.ok(orderService.getAllOrdersFromCustomerId(tempCustomerId));
    }


    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDto){
        Order newOrder = new Order();
        newOrder.setId(orderService.generateNewId());
        newOrder.setOrderDate(orderDto.getOrderDate());

        newOrder.setOrderItems(orderDto.getOrderItems());

        newOrder.setCard(orderDto.getCard());
        newOrder.setStatus(orderDto.getStatus());
        newOrder.setCustomer(orderDto.getCustomer());
        newOrder.setIdCustomer(orderDto.getIdCustomer());
        newOrder.setShippingAddress(orderDto.getShippingAddress());
        newOrder.setStateTax(orderDto.getStateTax());
        newOrder.setTPS(orderDto.getTPS());
        newOrder.setTTC(orderDto.getTTC());
        orderService.addOrder(newOrder);
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("order/modify/{id}")
    public ResponseEntity<Order> modifyOrder(@PathVariable String id, @RequestBody OrderDTO orderDto){
        Order updatedOrder = orderService.getOrderById(id);
        updatedOrder.setOrderDate(orderDto.getOrderDate());
        updatedOrder.setOrderItems(orderDto.getOrderItems());
        updatedOrder.setCard(orderDto.getCard());
        updatedOrder.setStatus(orderDto.getStatus());
        updatedOrder.setCustomer(orderDto.getCustomer());
        updatedOrder.setIdCustomer(id);
        updatedOrder.setShippingAddress(orderDto.getShippingAddress());
        updatedOrder.setStateTax(orderDto.getStateTax());
        updatedOrder.setTPS(orderDto.getTPS());
        updatedOrder.setTTC(orderDto.getTTC());
        return ResponseEntity.ok(updatedOrder);
    }



}
