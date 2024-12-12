package com.jxhifi.jxhifispring.services;


import com.jxhifi.jxhifispring.DTO.product.ProductSummaryTableDTO;
import com.jxhifi.jxhifispring.entities.Product;
import com.jxhifi.jxhifispring.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling operations related to products.
 * It provides methods to retrieve, create, and manage product entities.
 */
@Service
@Transactional
public class ProductService {
    private static long idNumber = 1L;

    private final ProductRepository productRepository;

    private final EntityManager entityManager;

    public ProductService(ProductRepository productRepository,EntityManager entityManager) {
        this.productRepository = productRepository;
        this.entityManager = entityManager;

    }

    /**
     * Initializes the idNumber with the next available unique identifier.
     * This method runs after the bean's properties have been initialized.
     */
    @PostConstruct
    private void initNumber() {
        Optional<Product> lastProductOptional = this.productRepository.findTopByIdNumericPart();

        if (lastProductOptional.isPresent()) {
            String lastId = lastProductOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3));
        }
    }

    /**
     * Retrieves a list of all products.
     *
     * @return a List of Product objects.
     */
    public List<Product> getAllProducts() {

        return productRepository.findAllOrderByIdNumericPart();
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
     * Retrieves a list of products that are currently on sale.
     *
     * @param onSale a boolean flag indicating whether to filter products that are on sale.
     *               If true, returns products that are on sale; if false, returns products
     *               that are not on sale.
     * @return an Optional containing a list of Product objects that match the on-sale
     * condition if found, or an empty Optional if no such products are found.
     */
    public Optional<List<Product>> findByOnSale(boolean onSale) {
        return productRepository.findByOnSale(onSale);
    }

    /**
     * Retrieves a list of products belonging to the specified category.
     *
     * @param categoryId the unique identifier of the category for which products are to be retrieved.
     * @return an Optional containing a list of Product objects belonging to the specified category
     * if found, or an empty Optional if no products are found for the given category.
     */
    public Optional<List<Product>> getProductByCategory(String categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }

    /**
     * Creates a new product with a unique identifier and saves it to the repository.
     *
     * @param product the Product object to be created.
     *
     */
    public void createNewProduct(Product product) {
        product.setId(generateNewId());

       productRepository.save(product);
    }
    public void deleteProduct(Product product){
        productRepository.delete(product);
    }
    /**
     * Generates a new unique identifier for products.
     *
     * @return a String value representing the new unique product identifier.
     */
    public synchronized String generateNewId() {
        String id = "PRO"+idNumber;
        idNumber++;
        return id ;
    }


    /**
     * Updates an existing product by saving the provided product to the repository.
     *
     * @param product the Product object to be updated.
     */
    @Transactional
    public void updateProduct(Product product) {
        Product managedProduct = entityManager.merge(product);
        productRepository.save(managedProduct);
    }

    public List<ProductSummaryTableDTO> getProductsTable(){
        return productRepository.findAllProductSummaryTableDTO();
    }


}
