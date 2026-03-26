package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.Review;
import com.csc340.AccessAble.Service.ReviewService;

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

    @PostMapping("/provider/{providerId}")
    public ResponseEntity<Review> createReview(@PathVariable Long providerId,
            @RequestBody Review review) {
        try {
            return ResponseEntity.ok(
                    reviewService.createReview(providerId, review));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
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

    @GetMapping("/provider/{providerId}/rating")
    public ResponseEntity<Map<String, Double>> getAverageRating(@PathVariable Long providerId) {
        double avg = reviewService.getAverageRating(providerId);

        Map<String, Double> response = new HashMap<>();
        response.put("averageRating", avg);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/provider/{providerId}/review/{reviewId}/reply")
    public ResponseEntity<Review> replyToReview(
            @PathVariable Long providerId,
            @PathVariable Long reviewId,
            @RequestBody Map<String, String> body) {

        String reply = body.get("reply");

        Review updated = reviewService.replyToReview(providerId, reviewId, reply);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
