package com.csc340.AccessAble.Service;

import org.springframework.stereotype.Service;

import com.csc340.AccessAble.Entities.Provider;
import com.csc340.AccessAble.Entities.Review;
import com.csc340.AccessAble.Repository.*;

import java.util.*;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProviderRepository providerRepository;

    public ReviewService(ReviewRepository reviewRepository,
            ProviderRepository providerRepository) {
        this.reviewRepository = reviewRepository;
        this.providerRepository = providerRepository;
    }

    public Review createReview(Long providerId, Review review) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        review.setProvider(provider);
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

    public double getAverageRating(Long providerId) {
        List<Review> reviews = reviewRepository.findByProviderId(providerId);

        if (reviews.isEmpty())
            return 0;

        int sum = 0;

        for (Review review : reviews) {
            sum += review.getRating();
        }

        return (double) sum / reviews.size();
    }

    public Review replyToReview(Long providerId, Long reviewId, String reply) {

        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setReply(reply);
        review.setProvider(provider);

        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(review);
    }

}
