package com.jxhifi.jxhifispring.DTO;

import com.jxhifi.jxhifispring.DTO.customer.AddressDTO;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.jxhifi.jxhifispring.entities.Store}
 */
@Value
public class  StoreDto implements Serializable {
    String id;
    String name;
    AddressDTO address;
    String telephone;
    String email;
    String manager;
}