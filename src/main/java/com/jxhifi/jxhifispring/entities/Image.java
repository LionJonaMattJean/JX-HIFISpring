package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "url", nullable = false)
    private String url;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}