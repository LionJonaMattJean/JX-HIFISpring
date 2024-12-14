package com.jxhifi.jxhifispring.DTO.Order;

import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OderItemDTO implements java.io.Serializable {
    private String id;
    private Product product;
    private int quantity;
    private double subTotal;
    private Order order;
}