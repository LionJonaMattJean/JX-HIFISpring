package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private int star;
    private String title;
    //todo add the relation with User that write the review
    @Column(length = 2000)
    private String Review;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}