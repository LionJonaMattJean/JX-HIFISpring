package com.jxhifi.jxhifispring.DTO.product;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewDTO  {

    // Getters and Setters
    private String UserId;
    private String content;
    private double rating;

}
