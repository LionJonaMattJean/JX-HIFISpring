package com.jxhifi.jxhifispring.DTO.product;

import lombok.Getter;
import lombok.Setter;

public class ShoppingCartItemDTO {

    //Getters & Setters
    @Getter @Setter
    private String productId;
    @Getter @Setter
    private int quantity;

}
