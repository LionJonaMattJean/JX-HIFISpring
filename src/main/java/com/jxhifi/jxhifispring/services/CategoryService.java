package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Category;

import com.jxhifi.jxhifispring.repositories.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to Category entities.
 * This class provides methods for retrieving, creating, and managing
 * categories in the repository.
 */
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private static long idNumber = 111L;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Initializes the idNumber with the next available unique identifier.
     * This method runs after the bean's properties have been initialized.
     */
    @PostConstruct
    private  void initNumber(){
        Optional<Category> lastCategoryOptional= this.categoryRepository.findTopByIdNumericPart();

        if(lastCategoryOptional.isPresent()){
            String lastId = lastCategoryOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3));
        }
    }

    /**
     * Retrieves a category from the repository by its unique identifier.
     *
     * @param id the unique identifier of the category to retrieve.
     * @return an Optional containing the Category if it exists, otherwise an empty Optional.
     */
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    /**
     * Retrieves a list of all category entities from the repository.
     *
     * @return a List of Category objects.
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Creates a new category by assigning a unique identifier and saving it to the repository.
     *
     * @param category the Category object to be created.
     * @return the saved Category object with a new unique identifier.
     */
    public Category createNewCategory(Category category) {
        category.setId("CAT"+generateNewId());
        return categoryRepository.save(category);
    }

    /**
     * Generates a new unique identifier for products.
     *
     * @return a long value representing the new unique product identifier.
     */
    private synchronized long generateNewId() {
        return idNumber+111;
    }
}
