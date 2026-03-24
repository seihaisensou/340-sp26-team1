package com.csc340.AccessAble.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csc340.AccessAble.Entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  @Query(value = "SELECT r.* FROM reviews r WHERE r.listing_id = :listingId", nativeQuery = true)
  List<Review> findByListingId(Long listingId);

  @Query(value = "SELECT r.* FROM reviews r WHERE r.customer_id = :customerId", nativeQuery = true)
  List<Review> findByCustomerId(Long customerId);

  @Query(value = "SELECT r.* FROM reviews r WHERE r.booking_id = :bookingId", nativeQuery = true)
  List<Review> findByBookingId(Long bookingId);

}