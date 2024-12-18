package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.DTO.ConvertAddressDTO_To_Address;
import com.jxhifi.jxhifispring.DTO.StoreDto;
import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.entities.Store;
import com.jxhifi.jxhifispring.services.AddressService;
import com.jxhifi.jxhifispring.services.StoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;


@RestController
@RequestMapping("/api/stores")
public class StoreController extends ConvertAddressDTO_To_Address {
    @Autowired
    private StoreService storeService;
    @Autowired
    private AddressService addressService;

    @GetMapping()
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable String id) {
        return ResponseEntity.ok(storeService.getStore(id).orElseThrow(() -> new RuntimeException("Store not found")));
    }

    @PostMapping("/create")
    public ResponseEntity<Store> createStore(@RequestBody StoreDto storeDTO) {
        Store store = new Store();
        store.setAddress(addressDTOToAddress(storeDTO.getAddress()));
        store.setName(storeDTO.getName());
        store.setTelephone(storeDTO.getTelephone());
        store.setEmail(storeDTO.getEmail());
        store.setManager(storeDTO.getManager());

        return ResponseEntity.ok(storeService.addStore(store));
    }

    @Transactional
    @PutMapping("/update/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable String id, @RequestBody StoreDto storeDTO) {
        Store store = storeService.getStore(id).orElseThrow(() -> new RuntimeException("Store not found"));
        store.setId(storeDTO.getId());
        store.setName(storeDTO.getName());
        store.setTelephone(storeDTO.getTelephone());
        store.setEmail(storeDTO.getEmail());
        store.setManager(storeDTO.getManager());

        Address address = addressDTOToAddress(storeDTO.getAddress());
        store.setAddress(address);

        //TODO supprimer si la convertion est utile
//        address.setAddress(storeDTO.getAddress().getAddress());
//        address.setProvince(storeDTO.getAddress().getProvince());
//        address.setCity(storeDTO.getAddress().getCity());
//        address.setCountry(storeDTO.getAddress().getCountry());
//        address.setPostalCode(storeDTO.getAddress().getPostalCode());

        storeService.updateStore(store);
        return ResponseEntity.ok(store);

    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteStore(@PathVariable String id) {
        Store store = storeService.getStore(id).orElseThrow(() -> new RuntimeException("Store not found"));

        storeService.deleteStore(store);
    }
}
