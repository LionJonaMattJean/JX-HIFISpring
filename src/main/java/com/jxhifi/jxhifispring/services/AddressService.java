package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.repositories.AddressRepositery;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepositery addressRepositery;
    private static long idNumber = 1L;

    @PostConstruct
    public void init() {
        Optional<Address> optionalAddress=addressRepositery.findTopByIdNumericPart();
        if(optionalAddress.isPresent()){
            String lastId=optionalAddress.get().getId();
            idNumber=Long.parseLong(lastId.substring(3))+1;
        }
    }
public Address addAddress(Address address){
        address.setId(generateId());
        return addressRepositery.save(address);
}
    public synchronized String generateId(){
        String id="ADR"+idNumber;
        idNumber++;
        return id;
    }
}
