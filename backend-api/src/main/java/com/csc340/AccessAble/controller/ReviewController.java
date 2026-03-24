package com.csc340.AccessAble.controller;

import com.csc340.AccessAble.entity.Review;
import com.csc340.AccessAble.service.ReviewService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);

        return review.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Review>> getReviewsByProvider(@PathVariable Long providerId) {
        List<Review> reviews = reviewService.getReviewsByProvider(providerId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}/reply")
    public ResponseEntity<Review> replyToReview(
            @PathVariable Long reviewId,
            @RequestParam Long providerId,
            @RequestBody String reply) {

        try {
            Review updated = reviewService.replyToReview(providerId, reviewId, reply);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
