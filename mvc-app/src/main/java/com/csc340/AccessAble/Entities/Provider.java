package com.csc340.AccessAble.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "providers")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Provider extends User {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private String credentials;

    private String profileImagePath;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Listing> listings = new ArrayList<>();

    @ManyToMany(mappedBy = "providers")
    @JsonIgnore
    private List<Customer> customers = new ArrayList<>();

    public Provider(String email, String password, String firstName, String lastName, String credentials) {
        super(email, password, "PROVIDER");
        this.firstName = firstName;
        this.lastName = lastName;
        this.credentials = credentials;
    }
}