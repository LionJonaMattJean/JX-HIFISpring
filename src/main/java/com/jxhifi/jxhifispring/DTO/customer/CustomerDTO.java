package com.jxhifi.jxhifispring.DTO.customer;

import lombok.Data;

@Data
public class CustomerDTO implements java.io.Serializable{
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private boolean isDeleted;
    private AddressDTO address;
}
