package com.jxhifi.jxhifispring.controlleur;


import com.jxhifi.jxhifispring.DTO.Order.DashboardVersion_OrderDTO;
import com.jxhifi.jxhifispring.DTO.Order.OrderDTO;
import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.services.CustomerService;
import com.jxhifi.jxhifispring.services.OrderItemService;
import com.jxhifi.jxhifispring.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/table/orders")
    public ResponseEntity<List<DashboardVersion_OrderDTO>> getAllOrdersTable() {
        return ResponseEntity.ok(orderService.getAllOrdersTable());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/order/modify/{id}")
    public ResponseEntity<OrderDTO> modifyOrder(@PathVariable String id, @RequestBody OrderDTO oDTO) {

        Order updatedOrder = orderService.updateOrder(id,oDTO);

        // Convert the updated Order entity to an OrderDTO
        OrderDTO updatedOrderDTO = orderService.orderToDTO(updatedOrder);

        return ResponseEntity.ok(updatedOrderDTO);
    }


    //tempCustomerid pourait etre a la fin du URL, TBD
    @GetMapping("/{tempCustomerId}/account-details/orders")
    public ResponseEntity<List<Order>> getOrdersFromCustomer(@PathVariable String tempCustomerId) {
        return ResponseEntity.ok(orderService.getAllOrdersFromCustomerId(tempCustomerId));
    }

//    @PostMapping("/createOrder")
//    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDto) {
//        Order newOrder = new Order();
//        newOrder.setId(orderService.generateNewId());
//        newOrder.setOrderDate(orderDto.getOrderDate());
//
//        newOrder.setOrderItems(orderDto.getOrderItems());
//
//        newOrder.setCard(orderDto.getCard());
//        newOrder.setStatus(orderDto.getStatus());
//        newOrder.setCustomer(orderDto.getCustomer());
////        newOrder.setIdCustomer(orderDto.getIdCustomer());
//        newOrder.setShippingAddress(orderDto.getShippingAddress());
//        newOrder.setStateTax(orderDto.getStateTax());
//        newOrder.setTPS(orderDto.getTPS());
//        newOrder.setTTC(orderDto.getTTC());
//        orderService.addOrder(newOrder);
//        return ResponseEntity.ok(newOrder);
//    }
}