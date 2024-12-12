package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.entities.Category;
import com.jxhifi.jxhifispring.repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping( "/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(categoryRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Category not found")
        ));
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping( "/categories/modify")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryRepository.save(category));
    }

}
