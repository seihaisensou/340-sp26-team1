package com.csc340.AccessAble.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private int rating;
    private String reply;
    private String comment;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "listing_id")
    private Listing listing;

    public Review(){

    }

    public Long getReviewId(){
        return reviewId;
    }

    public int getRating(){
        return rating;
    }

    public String getReply(){
        return reply;
    }

    public String getComment(){
        return comment;
    }

    public Provider getProvider(){
        return provider;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Listing getListing(){
        return listing;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public void setReply(String reply){
        this.reply = reply;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setProvider(Provider provider){
        this.provider = provider;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setListing(Listing listing){
        this.listing = listing;
    }
}
