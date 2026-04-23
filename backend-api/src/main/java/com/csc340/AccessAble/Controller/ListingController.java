package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Service.*;
import com.csc340.AccessAble.Entities.*;
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

    @PostMapping("/provider/{providerId}")
    public ResponseEntity<Listing> createListing(@PathVariable Long providerId,
            @RequestBody Listing listing) {
        try {
            Listing created = listingService.createListing(providerId, listing);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Listing>> getAllListings() {
        return new ResponseEntity<>(listingService.getAllListings(), HttpStatus.OK);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Listing>> getByProvider(@PathVariable Long providerId) {
        return new ResponseEntity<>(listingService.getListingsByProvider(providerId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getById(@PathVariable Long id) {
        return listingService.getListingById(id)
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> update(@PathVariable Long id,
            @RequestBody Listing listing) {
        try {
            return new ResponseEntity<>(listingService.updateListing(id, listing), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        listingService.deleteListing(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}