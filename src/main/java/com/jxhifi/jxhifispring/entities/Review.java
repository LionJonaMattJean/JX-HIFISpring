package com.jxhifi.jxhifispring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;



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
    @JsonIgnore
    private Product product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}