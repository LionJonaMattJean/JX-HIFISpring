package com.jxhifi.jxhifispring.DTO.product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
