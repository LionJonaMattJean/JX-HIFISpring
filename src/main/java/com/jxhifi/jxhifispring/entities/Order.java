package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.smartcardio.Card;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String idCustomer;
    //private Card card;
    //private OrderItem[] orderItems;
    private double TPS;
    private double stateTax;
    private double TTC;
    private String status;
    private Date orderDate;

    @OneToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;



    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
