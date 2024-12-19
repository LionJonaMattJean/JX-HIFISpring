package com.jxhifi.jxhifispring.DTO.Order;

import lombok.Data;

@Data
public class DD_OrderItem_ProductDTO {
    private String id;
    private String brand;
    private String name;

    private double sellPrice;
    private double specialPrice;

    private boolean onSale;
    private int stock;
}
