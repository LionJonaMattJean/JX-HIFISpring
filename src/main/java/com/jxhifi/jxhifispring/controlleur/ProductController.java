package com.jxhifi.jxhifispring.controlleur;
import com.jxhifi.jxhifispring.DTO.ImageDTO;
import com.jxhifi.jxhifispring.DTO.ProductDTO;
import com.jxhifi.jxhifispring.DTO.ShortSpecificationDTO;
import com.jxhifi.jxhifispring.DTO.SpecificationDetailsDTO;
import com.jxhifi.jxhifispring.entities.*;
import com.jxhifi.jxhifispring.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImageService imageService;
    private final ShortSpecificationService shortSpecificationService;
    private final SpecificationDetailsService specificationDetailsService;

    public ProductController(ProductService productService, CategoryService categoryService, ImageService imageService, ShortSpecificationService shortSpecificationService, SpecificationDetailsService specificationDetailsService) {
        this.productService = productService;
        this.categoryService=categoryService;
        this.imageService = imageService;
        this.shortSpecificationService = shortSpecificationService;
        this.specificationDetailsService = specificationDetailsService;
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
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
       Product product=new Product();
       product.setId(productService.generateNewId());
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setDescription(productDTO.getDescription());
        product.setSellPrice(productDTO.getSellPrice());
        product.setCostPrice(productDTO.getCostPrice());
        product.setSpecialPrice(productDTO.getSpecialPrice());
        product.setOnSale(productDTO.isOnSale());
        product.setStock(productDTO.getStock());
        product.setColors(productDTO.getColors());

        Category category = categoryService.getCategoryById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        product.setImages(productDTO.getImages().stream()
                .map(imageDTO -> {
                    Image image = new Image();
                    image.setId(imageService.generateNewId());
                    image.setUrl(imageDTO.getUrl());
                    image.setProduct(product);
                    return image;
                }).collect(Collectors.toList())
        );
        product.setShortSpecifications(productDTO.getShortSpecifications().stream()
                .map(shortSpecificationDTO -> {
                    ShortSpecification shortSpecification = new ShortSpecification();
                    shortSpecification.setId(shortSpecificationService.generateNewId());
                    shortSpecification.setProduct(product);
                    shortSpecification.setTitle(shortSpecificationDTO.getTitle());
                    shortSpecification.setDescription(shortSpecificationDTO.getDescription());
                    return shortSpecification;
                }).collect(Collectors.toList())
        );
        product.setSpecificationDetails(productDTO.getSpecificationDetails().stream()
                .map(specificationDetailsDTO -> {
                    SpecificationDetails specificationDetails = new SpecificationDetails();
                    specificationDetails.setId(specificationDetailsService.generateNewId());
                    specificationDetails.setProduct(product);
                    specificationDetails.setTitle(specificationDetailsDTO.getTitle());
                    specificationDetails.setDescription(specificationDetailsDTO.getDescription());
                    return specificationDetails;
                }).collect(Collectors.toList())
        );
        productService.createNewProduct(product);
        return ResponseEntity.ok(product);
    }
    @PutMapping("/products/modify/{id}")
    public ResponseEntity<Product> modifyProduct(@RequestBody ProductDTO productDTO, @PathVariable String id) {
        Product prod=productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        prod.setName(productDTO.getName());
        prod.setBrand(productDTO.getBrand());
        prod.setDescription(productDTO.getDescription());
        prod.setSellPrice(productDTO.getSellPrice());
        prod.setCostPrice(productDTO.getCostPrice());
        prod.setSpecialPrice(productDTO.getSpecialPrice());
        prod.setOnSale(productDTO.isOnSale());
        prod.setStock(productDTO.getStock());
        prod.setColors(productDTO.getColors());
        Category category = categoryService.getCategoryById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        prod.setCategory(category);



        updateImages(prod, productDTO.getImages());

        updateShortDescription(prod, productDTO.getShortSpecifications());
        updateSpecifications(prod, productDTO.getSpecificationDetails());

        productService.updateProduct(prod);
        return ResponseEntity.ok(prod);
    }

    private void updateImages(Product product, List<ImageDTO> imageDTOs) {
        // Remove images not in the new list
        product.getImages().removeIf(image ->
                imageDTOs.stream().noneMatch(dto -> image.getUrl().equals(dto.getUrl())));

        // Add new images
        for (ImageDTO dto : imageDTOs) {
            if (product.getImages().stream().noneMatch(img -> img.getUrl().equals(dto.getUrl()))) {
                Image newImage = new Image();
                newImage.setId(imageService.generateNewId());
                newImage.setUrl(dto.getUrl());
                newImage.setProduct(product);
                product.getImages().add(newImage);
            }
        }
    }
    private void updateShortDescription(Product product, List<ShortSpecificationDTO> shortSpecificationsDTOs){
        product.getShortSpecifications().removeIf(shortSpecification ->
                shortSpecificationsDTOs.stream().noneMatch(dto -> shortSpecification.getTitle().equals(dto.getTitle())));
        for (ShortSpecificationDTO dto : shortSpecificationsDTOs) {
            if (product.getShortSpecifications().stream().noneMatch(shortSpecification -> shortSpecification.getTitle().equals(dto.getTitle()))) {
                ShortSpecification newShortSpecification = new ShortSpecification();
                newShortSpecification.setId(shortSpecificationService.generateNewId());
                newShortSpecification.setProduct(product);
                newShortSpecification.setTitle(dto.getTitle());
                newShortSpecification.setDescription(dto.getDescription());
                product.getShortSpecifications().add(newShortSpecification);
            }
        }
    }
    private void updateSpecifications(Product product, List<SpecificationDetailsDTO> specificationDetailsDTOs){
        product.getSpecificationDetails().removeIf(specificationDetails ->
                specificationDetailsDTOs.stream().noneMatch(dto -> specificationDetails.getTitle().equals(dto.getTitle())));

        for (SpecificationDetailsDTO dto : specificationDetailsDTOs) {
            if (product.getSpecificationDetails().stream().noneMatch(specificationDetails -> specificationDetails.getTitle().equals(dto.getTitle()))) {
                SpecificationDetails newSpecificationDetails = new SpecificationDetails();
                newSpecificationDetails.setId(specificationDetailsService.generateNewId());
                newSpecificationDetails.setProduct(product);
                newSpecificationDetails.setTitle(dto.getTitle());
                newSpecificationDetails.setDescription(dto.getDescription());
                product.getSpecificationDetails().add(newSpecificationDetails);
            }
        }
    }
}