package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;



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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}