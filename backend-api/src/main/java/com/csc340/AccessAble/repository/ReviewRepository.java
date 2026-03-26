package com.csc340.AccessAble.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc340.AccessAble.Entities.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProviderId(Long providerId);
}
