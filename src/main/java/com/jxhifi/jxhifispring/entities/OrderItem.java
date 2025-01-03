package com.jxhifi.jxhifispring.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Order order;
}