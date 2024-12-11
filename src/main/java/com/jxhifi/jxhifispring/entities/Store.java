package com.jxhifi.jxhifispring.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String telephone;
    private String email;
    private String manager;

}
