package com.jxhifi.jxhifispring.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store {
    @Id

    private String id;
    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false)
    private Address address;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String manager;

}
