package com.jxhifi.jxhifispring.repositories;

import com.jxhifi.jxhifispring.DTO.product.ProductSearchDTO;
import com.jxhifi.jxhifispring.DTO.product.ProductSummaryTableDTO;
import com.jxhifi.jxhifispring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;
import java.util.Optional;
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, String> {
  /**
   * Finds the product with the highest numeric part in its ID.
   * The ID is expected to have a prefix, and the numeric part
   * follows the prefix. This method retrieves the product
   * by converting the numeric part to an integer for comparison.
   *
   * @return an Optional containing the product with the highest numeric
   *         value in its ID if found, or an empty Optional if no such
   *         product exists
   */
  @Query(value = "SELECT * FROM product ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
  Optional<Product> findTopByIdNumericPart();

  /**
   * Finds all products and orders them based on the numeric part of their ID.
   * The ID is expected to have a prefix followed by a numeric part,
   * and this method uses the numeric part to perform the ordering.
   *
   * @return a list of products ordered by the numeric part of their ID
   */
  @Query(value = "SELECT * FROM product ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED)", nativeQuery = true)
  List<Product> findAllOrderByIdNumericPart();

  @Query(value = "SELECT  new com.jxhifi.jxhifispring.DTO.product.ProductSummaryTableDTO(p.id, p.name,p.sellPrice,p.brand,p.stock,p.category.id)"+" from Product p")
  List<ProductSummaryTableDTO> findAllProductSummaryTableDTO();

  Optional<List<Product>> findByCategory_Id(String categoryId);

  Optional<List<Product>> findByOnSale(boolean onSale);

  /**
   * Retrieves a list of products with their ID and name formatted as ProductSearchDTO.
   * This method is used for search operations where only basic product details are required.
   *
   * @return a ResponseEntity containing a list of ProductSearchDTO objects representing
   *         the ID and name of products.
   */
  @Query(value = "select new com.jxhifi.jxhifispring.DTO.product.ProductSearchDTO(p.id,p.name) " +
          "from Product p")
    List<ProductSearchDTO> getProductsForSearch();

}