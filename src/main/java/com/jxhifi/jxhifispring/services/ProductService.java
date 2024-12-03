package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Product;
import com.jxhifi.jxhifispring.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Service class for handling operations related to products.
 * It provides methods to retrieve, create, and manage product entities.
 */
@Service
public class ProductService {
    private static long idNumber = 100012300000L;

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Initializes the idNumber with the next available unique identifier.
     * This method runs after the bean's properties have been initialized.
     */
    @PostConstruct
    private  void initNumber(){
        Optional<Product> lastProductOptional= this.productRepository.findTopByOrderByIdDesc();

        if(lastProductOptional.isPresent()){
            String lastId = lastProductOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3)) + 537;
        }
    }
    /**
     * Retrieves a list of all products.
     *
     * @return a List of Product objects.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the unique identifier of the product.
     * @return an Optional containing the Product if found, or empty if not found.
     */
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    /**
     * Creates a new product with a unique identifier and saves it to the repository.
     *
     * @param product the Product object to be created.
     * @return the created Product object.
     */
     public Product createNewProduct(Product product) {
        product.setId("PRO"+generateNewId());
        return productRepository.save(product);
     }
    /**
     * Generates a new unique identifier for products.
     *
     * @return a long value representing the new unique product identifier.
     */
    private synchronized long generateNewId() {
        return idNumber++;
    }
}
