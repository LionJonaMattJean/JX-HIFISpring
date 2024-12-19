package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.DTO.Order.DashboardDetail_OrderDTO;
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
    public ResponseEntity<List<DashboardVersion_OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    //TODO a supprimer si la version OrderDTO uniquement et suffisante.
//    @GetMapping("/order-detail_dshb/{id}")
//    public ResponseEntity<DashboardDetail_OrderDTO> getOrderByIdForDashboardDetail(@PathVariable String id) {
//        return ResponseEntity.ok(orderService.getOrderByIdForDashboardDetail(id));
//    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/order/modify/{id}")
    public ResponseEntity<Order> modifyOrder(@PathVariable String id, @RequestBody OrderDTO oDTO) {
        //Recup l'order existant a partir de l'id
        Order order = orderService.getExistingOrder(id);

        order.setTPS(oDTO.getTPS());
        order.setStateTax(oDTO.getStateTax());
        order.setTTC(oDTO.getTotalAmount());
        order.setStatus(oDTO.getStatus());
        order.setOrderDate(oDTO.getOrderDate());


        if (oDTO.getCard() != null) {
            order.getCard().setCardNumber(oDTO.getCard().getCardNumber());
            order.getCard().setExpiryDate(oDTO.getCard().getExpiryDate());
            order.getCard().setCvc(oDTO.getCard().getCvc());
        }

        if (oDTO.getShippingAddress() != null) {
            order.getShippingAddress().setAddress(oDTO.getShippingAddress().getAddress());
            order.getShippingAddress().setCity(oDTO.getShippingAddress().getCity());
            order.getShippingAddress().setProvince(oDTO.getShippingAddress().getProvince());
            order.getShippingAddress().setPostalCode(oDTO.getShippingAddress().getPostalCode());
            order.getShippingAddress().setCountry(oDTO.getShippingAddress().getCountry());
        }

        if (oDTO.getCustomer() != null) {
            order.getCustomer().setEmail(oDTO.getCustomer().getEmail());
            order.getCustomer().setPhone(oDTO.getCustomer().getPhone());
        }

        // MàJ des items
//        if (oDTO.getOrderItems() != null) {
//            order.getOrderItems().clear();
//
//            order.getOrderItems().addAll(oDTO.getOrderItems());
//        }

        return ResponseEntity.ok(order);
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