package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "specification_details")
public class SpecificationDetails {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}