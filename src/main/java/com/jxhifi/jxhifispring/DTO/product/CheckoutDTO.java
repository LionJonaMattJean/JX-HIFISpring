package com.jxhifi.jxhifispring.DTO.product;

import lombok.Getter;
import lombok.Setter;

public class CheckoutDTO implements java.io.Serializable{

    @Getter @Setter
    private String addressId;
    @Getter @Setter
    private String cardId;

}
