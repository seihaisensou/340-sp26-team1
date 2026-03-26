package com.csc340.AccessAble.Service;

import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritesService {
    
    @Autowired
    private final FavoritesRepository favoritesRepository;
    private final ListingRepository listingRepository;
    private final CustomerRepository customerRepository;

    public FavoritesService(ListingRepository listingRepository, CustomerRepository customerRepository, FavoritesRepository favoritesRepository) {
        this.listingRepository = listingRepository;
        this.favoritesRepository = favoritesRepository;
        this.customerRepository = customerRepository;
    }
    
    public Favorites createFavorites(Favorites favorite, long customerId,  long listingId) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        favorite.setCustomer(customer);
        favorite.setListing(listing);
        return favoritesRepository.save(favorite);
    }
    
    public Optional<Favorites> getFavoritesById(Long id) {
        return favoritesRepository.findById(id);
    }
    
    public List<Favorites> getAllFavorites() {
        return favoritesRepository.findAll();
    }
    
    public List<Favorites> getFavoritesByCustomerId(Long customerId) {
        return favoritesRepository.findByCustomerId(customerId);
    }
    
    public void deleteFavorites(Long id) {
        favoritesRepository.deleteById(id);
    }
}