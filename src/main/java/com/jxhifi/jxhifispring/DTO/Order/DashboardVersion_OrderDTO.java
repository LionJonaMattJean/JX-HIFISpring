package com.jxhifi.jxhifispring.DTO.Order;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DashboardVersion_OrderDTO {
    private String id;
    private LocalDate orderDate;
    private int nbrProducts;
    private double total;
    private String status;
    private String customerId;
    private String customerMail;
}
