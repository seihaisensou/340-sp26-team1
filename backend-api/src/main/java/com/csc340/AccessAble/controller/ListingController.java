package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Service.ListingService;
import com.csc340.AccessAble.Entities.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @PostMapping("/provider/{providerId}") // post
    public ResponseEntity<Listing> createListing(@PathVariable Long providerId,
            @RequestBody Listing listing) {
        try {
            Listing created = listingService.createListing(providerId, listing);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping                                                 //get
    public ResponseEntity<List<Listing>> getAllListings() {
        return new ResponseEntity<>(listingService.getAllListings(), HttpStatus.OK);
    }

    @GetMapping("/provider/{providerId}") 
    public ResponseEntity<List<Listing>> getListingsByProvider(@PathVariable Long providerId) {
        return new ResponseEntity<>(listingService.getListingsByProvider(providerId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Long id) {
        Optional<Listing> listing = listingService.getListingById(id);

        return listing.map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}") // put
    public ResponseEntity<Listing> updateListing(@PathVariable Long id,
            @RequestBody Listing listingDetails) {
        try {
            Listing updated = listingService.updateListing(id, listingDetails);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") // delete
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        listingService.deleteListing(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}