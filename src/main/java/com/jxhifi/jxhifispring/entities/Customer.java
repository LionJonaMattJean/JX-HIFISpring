package com.jxhifi.jxhifispring.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends User {
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("customer")
    private List<Order> orders;

    @OneToMany
    private List<Review> reviews;
}
