package com.jxhifi.jxhifispring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass //Empeche Spring de mapper cette classe et précise que c'est un super Type pour d'autre classes
public abstract class User {
    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column( nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName = "";

    @Column(nullable = false)
    private String phone = "";

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToOne //Lazy fetch par default
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;
}
