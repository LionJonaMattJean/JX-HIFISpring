package com.jxhifi.jxhifispring.DTO.customer;

import lombok.Data;

@Data
public class AddressDTO implements java.io.Serializable {
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String country;
}
