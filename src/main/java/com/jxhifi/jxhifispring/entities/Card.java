package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id

    private String id;
    @Column(length = 16, nullable = false)
    private Long cardNumber;

    /*  j'ai utiliser LocalDate psq ce type d'objet peu etre utiliser pour juste des dates
        sans temps, ce qui est plus proche de ce qu'on veut pour les dates d'expiration
        open to change back to Date type if needed
     */
    @Column(nullable = false)
    private LocalDate expiryDate;
    @Column(nullable = false)
    private String paymentMethod;
    @Column(nullable = false)
    private int cvc;
    @Column(nullable = false)
    private String nameHolder;

    /*
     * je laisserai commenter ici au cas ou ce n'ai pas necessaire
     * */

    @OneToMany(mappedBy = "card")
    private List<Order> orders;

}
