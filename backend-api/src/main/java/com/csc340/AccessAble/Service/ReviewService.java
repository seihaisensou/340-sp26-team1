package com.csc340.AccessAble.Service;

import com.csc340.AccessAble.Entities.Review;
import com.csc340.AccessAble.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
    
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    
    /*public List<Review> getReviewsByListingId(Long listingId) {
        return reviewRepository.findByListingId(listingId);
    }*/
    
    public Review updateReview(Long id, Review reviewDetails) {
        return reviewRepository.findById(id).map(review -> {
            review.setCustomerRating(reviewDetails.getCustomerRating());
            review.setComment(reviewDetails.getComment());
            review.setReplyText(reviewDetails.getReplyText());
            return reviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review updateComment(Long id, Review reviewComment) {
        return reviewRepository.findById(id).map(review -> {
            review.setComment(reviewComment.getComment());
            return reviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review replyToReview(Long id, Review reviewReply) {
        return reviewRepository.findById(id).map(review -> {
            review.setReplyText(reviewReply.getReplyText());
            return reviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found"));
    }
    
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}