package com.csc340.AccessAble.service;

import org.springframework.stereotype.Service;

import com.csc340.AccessAble.entity.*;
import com.csc340.AccessAble.repository.*;

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

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByProvider(Long providerId) {
        return reviewRepository.findByProviderId(providerId);
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

}
