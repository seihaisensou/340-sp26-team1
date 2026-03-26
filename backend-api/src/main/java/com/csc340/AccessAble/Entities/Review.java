package com.csc340.AccessAble.Entities;

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
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Customer listing;

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
}
