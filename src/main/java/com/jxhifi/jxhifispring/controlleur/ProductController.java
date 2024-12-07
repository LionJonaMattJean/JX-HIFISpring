package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.entities.Product;
import com.jxhifi.jxhifispring.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/products/category/{CATid}")
    public ResponseEntity<Optional<List<Product>>> getProductByCategory(@PathVariable String CATid) {
        return ResponseEntity.ok(productService.getProductByCategory(CATid));
    }
    @GetMapping("/products/onSale")
    public ResponseEntity<Optional<List<Product>>> getProductBySale() {
        return ResponseEntity.ok(productService.findByOnSale(true));
    }
    
    @PostMapping("/products/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        this.productService.createNewProduct(product);
        return ResponseEntity.ok(product);
    }
    
}
