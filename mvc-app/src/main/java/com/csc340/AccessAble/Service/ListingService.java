package com.csc340.AccessAble.Service;

import org.springframework.stereotype.Service;

import com.csc340.AccessAble.Entities.Listing;
import com.csc340.AccessAble.Entities.Provider;
import com.csc340.AccessAble.Repository.*;

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

    public Listing saveListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Optional<Listing> getListingById(Long id) {
        return listingRepository.findById(id);
    }

    public List<Listing> getListingsByProvider(Long providerId) {
        return listingRepository.findByProviderId(providerId);
    }

    public List<Listing> getListingByDescription(String description) {
        return listingRepository.findByListingNameContainingIgnoreCase(description);    
    }

    

    public Listing updateListing(Long id, Listing updated) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        listing.setListingName(updated.getListingName());
        listing.setCategory(updated.getCategory());
        listing.setPricingType(updated.getPricingType());
        listing.setPrice(updated.getPrice());
        listing.setShortDescription(updated.getShortDescription());
        listing.setDetailedDescription(updated.getDetailedDescription());
        listing.setLocation(updated.getLocation());
        listing.setAvailability(updated.getAvailability());

        return listingRepository.save(listing);
    }

    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }
}