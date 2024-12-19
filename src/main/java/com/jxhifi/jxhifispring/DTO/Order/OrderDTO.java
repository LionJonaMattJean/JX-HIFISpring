package com.jxhifi.jxhifispring.DTO.Order;

import com.jxhifi.jxhifispring.DTO.customer.AddressDTO;
import com.jxhifi.jxhifispring.DTO.customer.CustomerDTO;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    private String id;
    private CardDTO card;
    private List<DashboardDetail_OrderItemDTO> orderItems;
    private double TPS;
    private double stateTax;
    private double totalAmount;
    private String status;
    private LocalDate orderDate;
    private AddressDTO shippingAddress;
    private CustomerDTO customer;
}