package com.jxhifi.jxhifispring.DTO.Order;

import lombok.Data;

@Data
public class DashboardDetail_OrderItemDTO {
    private DD_OrderItem_ProductDTO product;
    private int quantity;
    private double sousTotal;
}
