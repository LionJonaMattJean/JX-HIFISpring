package com.jxhifi.jxhifispring.DTO.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;



@Data
public class AdminDTO implements java.io.Serializable {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private AddressDTO address;
    @JsonProperty("isDeleted")
    private boolean isDeleted;

}
