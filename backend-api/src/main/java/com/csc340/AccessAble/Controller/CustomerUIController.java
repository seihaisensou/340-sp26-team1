package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Repository.CustomerRepository;
import com.csc340.AccessAble.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/sign-up")
    public String signUp() { 
        return "customer/sign-up";
    }

    @PostMapping("/sign-up/success") 
    public String newSignup(Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        return "redirect:/customer/login";
    }

    @GetMapping("/login") 
    public String showLogin() {
        return "customer/login";
    }

    @GetMapping("/account") // NTD
    public String showAccount(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        Customer customer = customerRepository.findByEmail(userDetails.getUsername());
        model.addAttribute("customer", customer);
        return "customer/account";
    }

    @PostMapping("/account/") // NTD
    public String updateAccount(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(customerRepository.findByEmail(userDetails.getUsername()).getId(), updatedCustomer);
        
        if (customer != null) {
      //   customerService.saveProfilePicture(customer, picture);
           return "redirect:/customer/account";
        } 
        else {
            return "redirect:/customer/account";
        }
    }
            
    

    @GetMapping("/listings")
    public String showListings(Model model) {
        model.addAttribute("listings", listingService.getAllListings());
        return "customer/listings";
    }

    @GetMapping("/favoritelistings") // NTD
    public String showFavorites(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        Customer customer = customerRepository.findByEmail(userDetails.getUsername());
        model.addAttribute("favorites", favoritesService.getFavoritesByCustomerId(customer.getId()));
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
    
    public String writeReview(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Review review, @PathVariable Long id) {
    Customer customer = customerRepository.findByEmail(userDetails.getUsername());
    Review newReview = reviewService.createReview(review, customer.getId(), id);
    if (review != null) {     
      return "redirect:/customer/listing/" + newReview.getListing().getListingId();
    } 
    else {
      return "redirect:/customers/servants/add?error=true";
        }
    }

    @GetMapping("/userreviews") // NTD
    public String showMyReviews(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        Customer customer = customerRepository.findByEmail(userDetails.getUsername());
        model.addAttribute("reviews", reviewService.getReviewsByCustomer(customer.getId()));
        return "customer/userreviews";
    }

    @PostMapping("listing/{id}/favorite") // NTD
    
    public String favoriteListing(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, @PathVariable Long id, Favorites favorite) {
    Customer customer = customerRepository.findByEmail(userDetails.getUsername());

    Listing listing = listingService.getListingById(id)
            .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));
    Favorites newFavorite = favoritesService.createFavorites(favorite,customer.getId(),id);
            
    if (listing != null) {     
      return "redirect:/customer/favoritelistings";
    } 
    else {
      return "redirect:/customers/favorites/add?error=true";
        }
    }

    @GetMapping("/bookings")
    public String showBookings (@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model){
        Customer customer = customerRepository.findByEmail(userDetails.getUsername());
        model.addAttribute("bookings", bookingService.getBookingByCustomerId(customer.getId()));
        return "customer/bookings";
    }

    @PostMapping("listing/{id}/book") // NTD
    
    public String bookListing(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, @PathVariable Long id, Booking booking) {
    Customer customer = customerRepository.findByEmail(userDetails.getUsername());

    Listing listing = listingService.getListingById(id)
            .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));
    Booking newBooking = bookingService.createBooking(booking,customer.getId(),id);
            
    if (listing != null) {     
      return "redirect:/customer/bookings";
    } 
    else {
      return "redirect:/customers/bookings/add?error=true";
        }
    }
   
}