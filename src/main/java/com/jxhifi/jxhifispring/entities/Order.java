package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "order")
public class Order {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
