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
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO order){
        Order newOrder = new Order();
        newOrder.setId(orderService.generateNewId());


        return ResponseEntity.ok(newOrder);
    }




}
