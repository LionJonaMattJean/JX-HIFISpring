package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Store;
import com.jxhifi.jxhifispring.repositories.StoreRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final EntityManager entityManager;

    public StoreService(StoreRepository storeRepository, EntityManager entityManager) {
        this.storeRepository = storeRepository;
        this.entityManager = entityManager;
    }

    public Store addStore(Store store) {
        return storeRepository.save(store);
    }


    public Store getStore(String id) {
        return storeRepository.findById(id);
    }
    public Store getStoreByAddress(Address address) {
        return storeRepository.findByAddress(address);
    }
    public List<Store> getAllStores(){
        return storeRepository.findAllStores();
    }


    public void updateStore(Store store){
        Store managedStore = entityManager.merge(store);
        storeRepository.save(managedStore);
    }

    public void updateStoreAddress(String id, Address address) {
        storeRepository.updateAddress(id, address);
    }
    public void updateStoreManager(String id, String manager){
        storeRepository.updateManager(id, manager);
    }
    public void updateStorePhone(String id, String phone){
        storeRepository.updateTelephone(id, phone);
    }
    public void updateStoreEmail(String id,String email){
        storeRepository.updateEmail(id, email);
    }

    public void deleteStore(Store store) {
        storeRepository.delete(store);
    }

    public void deleteStoreById(String id) {
        int converted = Integer.parseInt(id);
        storeRepository.deleteById(converted);
    }

}
