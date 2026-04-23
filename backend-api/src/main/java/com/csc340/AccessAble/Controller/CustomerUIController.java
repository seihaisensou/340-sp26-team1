package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/customer")
public class CustomerUIController {

    @Autowired
    private ListingService listingService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private FavoritesService favoritesService;

    @GetMapping("/signup")
    public String signUp() {
        return "customer/sign-up";
    }

    @PostMapping("/signup/success")
    public String newSignup(Listing listing) {
        listingService.saveListing(listing);
        return "redirect:/customer/account";
    }

    @GetMapping("/listings")
    public String showListings(Model model) {
        model.addAttribute("listings", listingService.getAllListings());
        return "customer/listings";
    }

    @GetMapping("/favorites")
    public String showFavorites(Model model) {
        model.addAttribute("favorites", favoritesService.getFavoritesByCustomerId((long)4));
        return "customer/favoritelistings";
    }

    @GetMapping("/listing/{id}")
    public String showListing(@PathVariable Long id, Model model) {

        Listing listing = listingService.getListingById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));

                

        model.addAttribute("listing", listing);
        model.addAttribute("reviews", reviewService.getReviewsByListingId(id));

        
        return "customer/listing";
    }

    @GetMapping("listing/writereview/{id}")    
    public String showReviewForm(@PathVariable Long id, Model model) {

        Listing listing = listingService.getListingById(id)
            .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));

        model.addAttribute("listing", listing);
        model.addAttribute("title", "Write a review for listing " + id);

        return "customer/writereview";
    }
    
    @PostMapping("listing/writereview/{id}")
    
    public String writeReview(Review review, @PathVariable Long id) {
    
    Review newReview = reviewService.createReview(review, 5, id);
    if (review != null) {     
      return "redirect:/customer/listing/" + newReview.getListing().getListingId();
    } 
    else {
      return "redirect:/customers/servants/add?error=true";
        }
    }

    @PostMapping("listing/{id}/favorite")
    
    public String favoriteListing(@PathVariable Long id, Favorites favorite) {
    
    Listing listing = listingService.getListingById(id)
            .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));
    Favorites newFavorite = favoritesService.createFavorites(favorite,4,id);
            
    if (listing != null) {     
      return "redirect:/customer/favorites";
    } 
    else {
      return "redirect:/customers/favorites/add?error=true";
        }
    }
   
}