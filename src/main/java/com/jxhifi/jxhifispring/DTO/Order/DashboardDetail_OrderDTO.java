package com.jxhifi.jxhifispring.DTO.Order;

import com.jxhifi.jxhifispring.DTO.customer.AddressDTO;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class DashboardDetail_OrderDTO {
    private String id;
    private LocalDate orderDate; // String pour formater directement la date
    private String status;
    private double totalAmount;

    private List<DashboardDetail_OrderItemDTO> orderItems;

    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerPhone;

    private AddressDTO shippingAddress;
}
