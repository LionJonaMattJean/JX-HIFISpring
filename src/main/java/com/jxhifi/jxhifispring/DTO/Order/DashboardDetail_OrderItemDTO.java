package com.jxhifi.jxhifispring.DTO.Order;

import lombok.Data;

@Data
public class DashboardDetail_OrderItemDTO {
    private String productName;
    private int quantity;
    private double sousTotal;
}
