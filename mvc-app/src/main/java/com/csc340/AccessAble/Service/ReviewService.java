package com.csc340.AccessAble.Service;

import org.springframework.stereotype.Service;

import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Repository.*;

import java.util.*;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ListingRepository listingRepository;
    private final CustomerRepository customerRepository;

    public ReviewService(ReviewRepository reviewRepository, ListingRepository listingRepository, CustomerRepository customerRepository) {
        this.reviewRepository = reviewRepository;
        this.listingRepository = listingRepository;
        this.customerRepository = customerRepository;
    }

    public Review createReview(Review review, long customerId,  long listingId) {

        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));        

        Provider provider = listing.getProvider();


        review.setCustomer(customer);
        review.setProvider(provider);
        review.setListing(listing);
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByProvider(Long providerId) {
        return reviewRepository.findByProviderId(providerId);
    }


    public List<Review> getReviewsByCustomer(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    public List<Review> getReviewsByListingId(Long listingId) {
        return reviewRepository.findByListing_ListingId(listingId);
    }

    public double getAverageRating(Long listingId) {
        List<Review> reviews = reviewRepository.findByListing_ListingId(listingId);

        if (reviews.isEmpty())
            return 0;

        int sum = 0;

        for (Review review : reviews) {
            sum += review.getRating();
        }

        return (double) sum / reviews.size();
    }

    public Review replyToReview(Long reviewId, String reply) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setReply(reply);

        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(review);
    }

    public Review editReview(Long reviewId, Review updated) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
                 
        if(updated.getComment() != null){
            review.setComment(updated.getComment());
        }
        Integer test = updated.getRating();
        if(test != null){
            review.setRating(updated.getRating());
        }
        if(updated.getReply() != null){
            review.setReply(updated.getReply());
        }
        

        return reviewRepository.save(review);
    }

}
