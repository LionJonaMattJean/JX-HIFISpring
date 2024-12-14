package com.jxhifi.jxhifispring.DTO.product;



import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter

public class ProductDTO implements java.io.Serializable {


    private String id;
    private String brand;
    private String name;
    private String description;

    private double sellPrice;
    private double costPrice;
    private double specialPrice;

    private boolean onSale;
    private int stock;

    // Accept category as an ID, not the full object
    private String categoryId;

    private List<String> colors;

    private List<SpecificationDetailsDTO> specificationDetails;

    private List<ShortSpecificationDTO> shortSpecifications;

    private List<ImageDTO> images;

    // Optionally include reviews, if needed for the response
    private List<ReviewDTO> reviews;

}