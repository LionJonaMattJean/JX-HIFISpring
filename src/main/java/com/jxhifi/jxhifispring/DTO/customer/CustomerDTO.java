package com.jxhifi.jxhifispring.DTO.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
public class CustomerDTO {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private boolean isDeleted;
    private AddressDTO address;
}
