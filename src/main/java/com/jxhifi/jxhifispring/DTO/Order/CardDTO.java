package com.jxhifi.jxhifispring.DTO.Order;

import com.jxhifi.jxhifispring.entities.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class CardDTO {
    private String id;
    private int cardNumber;
    private LocalDate expiryDate;
    private String paymentMethod;
    private int cvc;
    private String nameHolder;
    private List<Order> orders;
}
