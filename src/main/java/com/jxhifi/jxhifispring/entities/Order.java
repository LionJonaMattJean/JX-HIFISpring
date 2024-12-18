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
@Table(name = "`order`")
public class Order {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    //je laisserai ceci en commentaire au cas ou on ne l'utilisera pas
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    //TODO Clarifier nom attribut TPS != StateTax
    @Column(nullable = false)
    private double TPS;
    @Column(nullable = false)
    private double stateTax;
    @Column(nullable = false)
    private double TTC;
    @Column(nullable = false)
    private String status;

    /*  j'ai utiliser LocalDate psq ce type d'objet peu etre utiliser pour juste des dates
       sans temps, ce qui est plus proche de ce qu'on veut pour les dates d'expiration
       open to change back to Date type if needed,
    */
    @Column(nullable = false)
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn
    private Address shippingAddress;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    // TODO Supprimer et déplacer dans OrderDTO
    private String idCustomer;
}