package com.jxhifi.jxhifispring.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double subTotal;

    /*Je laisserai ca ici commenter au cas ou ce n'est pas necessaire*/
    /*
    * @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    *
    *
    * */

}
