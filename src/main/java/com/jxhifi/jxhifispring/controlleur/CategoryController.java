package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.entities.Category;
import com.jxhifi.jxhifispring.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping( "/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id).orElseThrow(RuntimeException::new)
        );
    }
    @DeleteMapping("/categories/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping( "/categories/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(
                categoryService.createNewCategory(category)
        );
    }
    @PutMapping("/categories/modify")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return ResponseEntity.ok(
                categoryService.updateCategory(category)
        );
    }

}
