package com.jxhifi.jxhifispring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Getter
    private int star;
    private String title;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @JsonProperty("customer_firstName")
    public String getCustomerName() {
        return customer != null ? customer.getFirstName() : null;
    }

    @Column(length = 2000)
    private String Review;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

}