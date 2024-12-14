package com.jxhifi.jxhifispring.DTO.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ProductSummaryTableDTO implements java.io.Serializable {
    private String id;
    private String name;
    private Double sellPrice;
    private String brand;
    private int stock;
    private String categoryId;

}
