package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.DTO.StoreDto;
import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Store;
import com.jxhifi.jxhifispring.services.AddressService;
import com.jxhifi.jxhifispring.services.StoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private AddressService addressService;

    @GetMapping()
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @PostMapping("/create")
    public ResponseEntity<Store> createStore(@RequestBody StoreDto storeDTO) {
        Store store=new Store();
        Address address= new Address();
        addressDtoToAddress(storeDTO,address);
        store.setAddress(address);
        store.setName(storeDTO.getName());
        store.setTelephone(storeDTO.getTelephone());
        store.setEmail(storeDTO.getEmail());
        store.setManager(storeDTO.getManager());

        return ResponseEntity.ok(storeService.addStore(store));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteStore(@PathVariable String id) {
        Store store=storeService.getStore(id).orElseThrow(()->new RuntimeException("Store not found"));

        storeService.deleteStore(store);
    }

    private void addressDtoToAddress(StoreDto storeDTO, Address address) {

        address.setId(addressService.generateId());
        address.setAddress(storeDTO.getAddress().getAddress());
        address.setProvince(storeDTO.getAddress().getProvince());
        address.setCity(storeDTO.getAddress().getCity());
        address.setCountry(storeDTO.getAddress().getCountry());
        address.setPostalCode(storeDTO.getAddress().getPostalCode());

    }
}
