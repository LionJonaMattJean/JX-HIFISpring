package com.jxhifi.jxhifispring.DTO.customer;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class NewAccountCustomerDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
