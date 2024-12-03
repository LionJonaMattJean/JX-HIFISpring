package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
public class Product {
    @Id
    private String id;

    private String brand;
    private String name;
    private String description;

    private double sellPrice;
    private double costPrice;
    private double specialPrice;

    private boolean onSale;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;



    @ElementCollection
    private List<String> colors;

    @OneToMany(mappedBy = "product")
    private List<SpecificationDetails> specificationDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ShortSpecification> shortSpecifications;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;


}
