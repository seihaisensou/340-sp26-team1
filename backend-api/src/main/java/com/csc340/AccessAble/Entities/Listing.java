package com.csc340.AccessAble.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "listings")
public class Listing {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingId;

    private String listingName;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Listing(){
    }

    public Listing(String listingName, Provider provider){
        this.listingName = listingName;
        this.provider = provider;
    }

    public Long getListingId(){
        return listingId;
    }

    public String getListingName(){
        return listingName;
    }

    public Provider getProvider(){
        return provider;
    }

    public void setListingName(String listingName){
        this.listingName = listingName;
    }

    public void setProvider(Provider provider){
        this.provider = provider;
    }
    
}
