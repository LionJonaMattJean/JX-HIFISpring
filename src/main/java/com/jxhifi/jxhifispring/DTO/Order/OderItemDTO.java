package com.jxhifi.jxhifispring.DTO.order;

import com.jxhifi.jxhifispring.entities.Order;
import com.jxhifi.jxhifispring.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OderItemDTO  {
    private String id;
    private Product product;
    private int quantity;
    private double subTotal;
    private Order order;
}
