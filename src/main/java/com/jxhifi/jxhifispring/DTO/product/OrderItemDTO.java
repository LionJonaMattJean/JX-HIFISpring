package com.jxhifi.jxhifispring.DTO.product;

public class OrderItemDTO {
    private String id;
    private String productId;
    private int quantity;

    public OrderItemDTO(String id, String name, int quantity) {
        this.id = id;
        this.productId = name;
        this.quantity = quantity;
    }
}
