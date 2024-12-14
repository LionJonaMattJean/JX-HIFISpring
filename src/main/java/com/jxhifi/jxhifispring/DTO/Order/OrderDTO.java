package com.jxhifi.jxhifispring.DTO.Order;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Card;
import com.jxhifi.jxhifispring.entities.Customer;
import com.jxhifi.jxhifispring.entities.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class OrderDTO  implements java.io.Serializable{
    private String id;
    private String idCustomer;
    private Card card;
    private List<OrderItem> orderItems;
    private double TPS;
    private double stateTax;
    private double TTC;
    private String status;
    private LocalDate orderDate;
    private Address shippingAddress;
    private Customer customer;

}
