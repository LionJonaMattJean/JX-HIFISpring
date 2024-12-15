package com.jxhifi.jxhifispring.DTO.customer;

import lombok.Data;

@Data
public class AdminDTO {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private AddressDTO address;
}
