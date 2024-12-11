package com.jxhifi.jxhifispring.services;


import com.jxhifi.jxhifispring.entities.SpecificationDetails;
import com.jxhifi.jxhifispring.repositories.SpecificationDetailsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecificationDetailsService {

    private static long idNumber = 1L;
    private final SpecificationDetailsRepository specificationDetailsRepository;

    public SpecificationDetailsService(SpecificationDetailsRepository specificationDetailsRepository) {
        this.specificationDetailsRepository = specificationDetailsRepository;
    }

    /**
     * Initializes the idNumber field using the ID of the most recent
     * SpecificationDetails entity present in the repository. If no such
     * entity is found, the idNumber remains unchanged. The ID from the
     * SpecificationDetails entity is expected to have a prefix, and
     * idNumber is derived by parsing the substring of the ID excluding
     * the first three characters. This method is executed after the
     * SpecificationDetailsService bean has been constructed and
     * dependencies are injected.
     */
    @PostConstruct
    private  void initNumber(){
        Optional<SpecificationDetails> lastSpecificationOptional= this.specificationDetailsRepository.findTopByIdNumericPart();

        if(lastSpecificationOptional.isPresent()){
            String lastId = lastSpecificationOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3));
        }
    }

    /**
     * Retrieves a list of SpecificationDetails entities associated with a specific product ID.
     *
     * @param id the unique identifier of the product whose specification details are to be retrieved
     * @return a list of Optional<SpecificationDetails> objects containing the specification details
     *         of the specified product
     */
    public List<SpecificationDetails> getSpecificationDetailByProductId(String id){
        return this.specificationDetailsRepository.findByProduct_Id(id);
    }
    /**
     * Creates a new SpecificationDetails object by assigning it a unique identifier
     * and saving it to the repository.
     *
     * @param specificationDetails the SpecificationDetails object to be created and saved
     * @return the saved SpecificationDetails object, now with a unique identifier
     */
    public SpecificationDetails createNewSpecificationDetails(SpecificationDetails specificationDetails) {
        specificationDetails.setId(generateNewId());
        return specificationDetailsRepository.save(specificationDetails);
    }
    /**
     * Generates a new unique identifier for products.
     *
     * @return a String value representing the new unique product identifier.
     */
    public synchronized String generateNewId() {
        String id = "SPE"+idNumber;
        idNumber++;
        return id ;
    }
}
