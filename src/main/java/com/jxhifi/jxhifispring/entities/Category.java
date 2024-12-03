package com.jxhifi.jxhifispring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String name;
    @Column(name = "description", nullable = false,length = 2000)
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}