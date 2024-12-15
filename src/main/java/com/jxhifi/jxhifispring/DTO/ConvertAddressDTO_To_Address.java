package com.jxhifi.jxhifispring.DTO;

import com.jxhifi.jxhifispring.DTO.customer.AddressDTO;
import com.jxhifi.jxhifispring.entities.Address;
import com.jxhifi.jxhifispring.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ConvertAddressDTO_To_Address {
    @Autowired
    private AddressService addressService;

    /**
     * Fonction de convertion d'une adressDTO vers une instance d'adresse pour la DB
     * @param addressDTO recu du front Angular
     * @return une instance d'address pour la DB
     */
    public Address addressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressService.generateId());
        address.setAddress(addressDTO.getAddress());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setProvince(addressDTO.getProvince());
        address.setPostalCode(addressDTO.getPostalCode());

        addressService.addAddress(address);

        return address;
    }
}
