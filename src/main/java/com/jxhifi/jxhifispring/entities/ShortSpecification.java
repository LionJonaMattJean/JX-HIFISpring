package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "short_specification")
public class ShortSpecification {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}