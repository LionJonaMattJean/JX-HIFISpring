package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;

@Entity
@Table(name="ShoppingCart")
public class ShoppingCart {

    @Id
    @Column(name="id", nullable = false)
    private int id;






}
