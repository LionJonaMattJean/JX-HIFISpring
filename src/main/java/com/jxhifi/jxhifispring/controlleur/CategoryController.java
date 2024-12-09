package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.repositories.CategoryRepository;

public class CategoryController {
    private final CategoryRepository categoryRepository;
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



}
