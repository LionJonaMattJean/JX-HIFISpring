package com.jxhifi.jxhifispring.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    private String id;

    @OneToOne
    @JoinColumn()
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double subTotal;

    /*Je laisserai ca ici commenter au cas ou ce n'est pas necessaire*/

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;


}