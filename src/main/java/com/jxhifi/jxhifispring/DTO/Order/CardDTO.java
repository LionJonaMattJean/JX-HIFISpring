package com.jxhifi.jxhifispring.DTO.Order;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CardDTO {
    private Long cardNumber;
    private LocalDate expiryDate;
    private int cvc;
    private String paymentMethod;
}
