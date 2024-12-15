package com.jxhifi.jxhifispring.DTO.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String country;
}
