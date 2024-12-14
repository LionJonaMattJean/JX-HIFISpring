package com.jxhifi.jxhifispring.DTO.product;

import lombok.Getter;
import lombok.Setter;

public class ShoppingCartItemDTO implements java.io.Serializable {

    //Getters & Setters
    @Getter @Setter
    private String productId;
    @Getter @Setter
    private int quantity;

}
