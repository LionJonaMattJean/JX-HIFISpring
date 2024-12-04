package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.ShortSpecification;
import com.jxhifi.jxhifispring.repositories.ShortSpecificationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShortSpecificationService {
    private static long idNumber = 1L;
    private final ShortSpecificationRepository shortSpecificationRepository;

    public ShortSpecificationService(ShortSpecificationRepository shortSpecificationRepository) {
        this.shortSpecificationRepository = shortSpecificationRepository;
    }

    /**
     * Initializes the idNumber field by extracting the numeric portion from the ID
     * of the most recent ShortSpecification entity, if present. This method is
     * automatically invoked after the ShortSpecificationService bean has been fully
     * initialized. The idNumber is set by parsing the substring of the ID starting
     * from the fourth character and converting it to a long. If no ShortSpecification
     * entities are found, the idNumber remains unchanged.
     */
    @PostConstruct
    private  void initNumber(){
        Optional<ShortSpecification> lastProductOptional= this.shortSpecificationRepository.findTopByOrderByIdDesc();

        if(lastProductOptional.isPresent()){
            String lastId = lastProductOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(4));
        }
    }

    /**
     * Retrieves a ShortSpecification entity by its unique identifier.
     *
     * @param id the unique identifier of the ShortSpecification to be retrieved.
     * @return an Optional containing the ShortSpecification if found, or an empty Optional if not found.
     */
    public Optional<ShortSpecification> getShortSpecificationById(String id) {
        return shortSpecificationRepository.findById(id);
    }

    public ShortSpecification createNewShortSpecification(ShortSpecification shortSpecification) {
        shortSpecification.setId("QSPE"+generateNewId());
        return shortSpecificationRepository.save(shortSpecification);
    }
    /**
     * Retrieves a list of ShortSpecification entities associated with a given product ID.
     *
     * @param id the unique identifier of the product whose ShortSpecifications are to be retrieved.
     * @return a list of Optional instances, each wrapping a ShortSpecification entity
     *         if found, or an empty Optional if not.
     */
    public List<ShortSpecification> getShortSpecificationByProductId(String id){
        return this.shortSpecificationRepository.findByProduct_Id(id);
    }
    /**
     * Generates a new unique identifier for products.
     *
     * @return a long value representing the new unique product identifier.
     */
    private synchronized long generateNewId() {
        return idNumber+1;
    }
}
