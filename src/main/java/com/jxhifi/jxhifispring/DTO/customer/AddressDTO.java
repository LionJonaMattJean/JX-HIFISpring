package com.jxhifi.jxhifispring.DTO.customer;

import lombok.Data;

@Data
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
