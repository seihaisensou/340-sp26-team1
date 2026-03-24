package com.csc340.AccessAble.repository;

import com.csc340.AccessAble.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByProviderId(Long providerId);
}