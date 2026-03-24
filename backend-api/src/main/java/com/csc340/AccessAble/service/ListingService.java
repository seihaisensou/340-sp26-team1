package com.csc340.AccessAble.service;

import org.springframework.stereotype.Service;

import com.csc340.AccessAble.entity.*;
import com.csc340.AccessAble.repository.*;

import java.util.*;

@Service
public class ListingService {

    private final ListingRepository listingRepository;
    private final ProviderRepository providerRepository;

    public ListingService(ListingRepository listingRepository,
            ProviderRepository providerRepository) {
        this.listingRepository = listingRepository;
        this.providerRepository = providerRepository;
    }

    public Listing createListing(Long providerId, Listing listing) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        listing.setProvider(provider);
        return listingRepository.save(listing);
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public List<Listing> getListingsByProvider(Long providerId) {
        return listingRepository.findByProviderId(providerId);
    }

    public Optional<Listing> getListingById(Long id) {
        return listingRepository.findById(id);
    }

    public Listing updateListing(Long id, Listing updated) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        if (updated.getListingName() != null) {
            listing.setListingName(updated.getListingName());
        }

        return listingRepository.save(listing);
    }

    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }
}