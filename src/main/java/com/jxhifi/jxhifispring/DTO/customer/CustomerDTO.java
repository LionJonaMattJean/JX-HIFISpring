package com.jxhifi.jxhifispring.DTO.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Data
public class CustomerDTO implements java.io.Serializable{
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
    private AddressDTO address;
}
