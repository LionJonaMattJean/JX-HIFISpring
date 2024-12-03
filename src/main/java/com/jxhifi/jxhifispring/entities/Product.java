package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.*;


import java.util.List;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        this.specialPrice = specialPrice;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<SpecificationDetails> getSpecificationDetails() {
        return specificationDetails;
    }

    public void setSpecificationDetails(List<SpecificationDetails> specificationDetails) {
        this.specificationDetails = specificationDetails;
    }

    public List<ShortSpecification> getShortSpecifications() {
        return shortSpecifications;
    }

    public void setShortSpecifications(List<ShortSpecification> shortSpecifications) {
        this.shortSpecifications = shortSpecifications;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
