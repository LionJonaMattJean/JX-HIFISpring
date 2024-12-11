package com.jxhifi.jxhifispring.services;
import com.jxhifi.jxhifispring.entities.Image;
import com.jxhifi.jxhifispring.repositories.ImageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing image-related operations. It handles interactions
 * with the ImageRepository to perform CRUD operations on Image entities.
 */
@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private static Long idNumber=1L;
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Initializes the idNumber field with the value extracted from the ID of the
     * most recently added Image entity in the repository, if present. This value
     * is derived by parsing the substring of the ID starting from the fourth
     * character and converting it to a Long. This method is automatically invoked
     * after the ImageService bean has been fully initialized.
     */
    @PostConstruct
    private void initNumber(){
        Optional<Image> lastImageOptional= this.imageRepository.findTopByIdNumericPart();

        if(lastImageOptional.isPresent()){
            String lastId = lastImageOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3));
        }
    }

    /**
     * Retrieves a list of all images stored in the repository.
     *
     * @return a List of Image objects representing all images available.
     */
    List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    /**
     * Retrieves an image by its unique identifier from the repository.
     *
     * @param id the unique identifier of the image.
     * @return an Optional containing the Image if found, or empty if not found.
     */
    Optional<Image> getImageById(String id) {
        return imageRepository.findById(id);
    }

    /**
     * Creates a new image entry in the repository with a uniquely generated identifier.
     *
     * @param image the Image object to be saved, which will have its ID set internally.
     * @return the saved Image object with the newly assigned ID.
     */
    public Image createNewImage(Image image) {
        image.setId(generateNewId());
        return imageRepository.save(image);
    }

    /**
     * Generates a new unique identifier for products.
     *
     * @return a String value representing the new unique product identifier.
     */
    public synchronized String generateNewId() {
        String id = "IMG"+idNumber;
        idNumber++;
        return id ;
    }
}
