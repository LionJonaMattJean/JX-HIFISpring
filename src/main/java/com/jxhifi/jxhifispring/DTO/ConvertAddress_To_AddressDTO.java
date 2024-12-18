package com.jxhifi.jxhifispring.DTO;

import com.jxhifi.jxhifispring.DTO.customer.AddressDTO;
import com.jxhifi.jxhifispring.entities.Address;

public abstract class ConvertAddress_To_AddressDTO {
    public AddressDTO addressToAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress(address.getAddress());
        addressDTO.setCity(address.getCity());
        addressDTO.setProvince(address.getProvince());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setCountry(address.getCountry());

        return addressDTO;
    }
}
