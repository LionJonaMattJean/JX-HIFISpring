package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
