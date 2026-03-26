package com.csc340.AccessAble.Entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "providers")
@PrimaryKeyJoinColumn(name = "id")
public class Provider extends User {

    private String firstName;
    private String lastName;
    private String credentials;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Listing> listings = new ArrayList<>();

    public Provider() {
        super();
    }

    public Provider(String email, String password, String firstName, String lastName, String credentials) {
        super(email, password, "PROVIDER");
        this.firstName = firstName;
        this.lastName = lastName;
        this.credentials = credentials;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCredentials() {
        return credentials;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }
}