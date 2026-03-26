package com.csc340.AccessAble.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csc340.AccessAble.Entities.Listing;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByProviderId(Long providerId);
    @Query(value = "SELECT l.* FROM listings l WHERE l.listingName like %?1%", nativeQuery = true)
    List<Listing> findbyDescription(String description);
}