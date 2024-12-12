package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Store;
import com.jxhifi.jxhifispring.repositories.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public Store addStore(Store store) {
        return storeRepository.save(store);
    }


    public Store getStore(String id) {
        return storeRepository.findById(id);
    }
    public Store getStoreByAddress(Address address) {
        return storeRepository.findByAddress(address);
    }


    public void updateStoreAddress(Address address) {
        storeRepository.updateAddress(address);
    }
    public void updateStoreManager(String manager){
        storeRepository.updateManager(manager);
    }
    public void updateStorePhone(String phone){
        storeRepository.updateTelephone(phone);
    }
    public void updateStoreEmail(String email){
        storeRepository.updateEmail(email);
    }


    public void deleteStore(String id) {
        int converted = Integer.parseInt(id);
        storeRepository.deleteById(converted);
    }

}