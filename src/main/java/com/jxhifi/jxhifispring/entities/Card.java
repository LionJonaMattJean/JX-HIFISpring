package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int cardNumber;
    private Date expiryDate;
    private String paymentMethod;
    private int cvc;
    private String nameHolder;

    /*
    * je laisserai commenter ici au cas ou ce n'ai pas necessaire
    * */

    // @OneToMany(mappedBy = "card")
    // private List<Order> orders;

}
