package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String idCustomer;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    //je laisserai ceci en commentaire au cas ou on ne l'utilisera pas
    @OneToMany//(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    private double TPS;
    private double stateTax;
    private double TTC;
    private String status;
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
