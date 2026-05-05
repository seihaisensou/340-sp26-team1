package com.csc340.AccessAble.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingId;

    @Column(nullable = false)
    private Double price;

    private String listingName;
    private String category;
    private String pricingType;
    private String shortDescription;
    private String detailedDescription;
    private String location;
    private String availability;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Listing() {
    }

    public Listing(String listingName, Provider provider) {
        this.listingName = listingName;
        this.provider = provider;
    }

    public Long getListingId() {
        return listingId;
    }

    public String getListingName() {
        return listingName;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getCategory() {
        return category;
    }

    public String getPricingType() {
        return pricingType;
    }

    public Double getPrice() {
        return price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public String getLocation() {
        return location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setListingName(String listingName) {
        this.listingName = listingName;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPricingType(String pricingType) {
        this.pricingType = pricingType;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

}
