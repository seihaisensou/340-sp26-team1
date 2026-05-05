package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Repository.CustomerRepository;
import com.csc340.AccessAble.Service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/403")
    public String wrongWay() { 
        return "customer/403";
    }

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
    public String updateAccount(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Customer updatedCustomer, MultipartFile picture) {
        Customer customer = customerService.updateCustomer(customerRepository.findByEmail(userDetails.getUsername()).getId(), updatedCustomer);
        
        if (customer != null) {
         customerService.saveProfilePicture(customer, picture);
           return "redirect:/customer/account";
        } 
        else {
            return "redirect:/customer/account";
        }
    }
            
    

    @GetMapping("/listings")
    public String showListings(Model model) {
        model.addAttribute("reviews", reviewService);
        model.addAttribute("listings", listingService.getAllListings());
        return "customer/listings";
    }

    @GetMapping("/listings/search")
    public String showListings(@RequestParam String search, Model model) {
        List<Listing> listing = listingService.getListingByDescription(search);
        listing.sort((l1, l2) -> {
        double first = reviewService.getAverageRating(l1.getListingId());
        double second = reviewService.getAverageRating(l2.getListingId());
        return Double.compare(second, first); 
            });
        model.addAttribute("reviews", reviewService);
        model.addAttribute("listings", listing);
        return "customer/listings";
    }

    

    @GetMapping("/favoritelistings") // NTD
    public String showFavorites(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        Customer customer = customerRepository.findByEmail(userDetails.getUsername());
         model.addAttribute("reviews", reviewService);
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
      return "redirect:/customers/listing/" + newReview.getListing().getListingId()+ "add?error=true";
        }
    }

    @GetMapping("/userreviews") // NTD
    public String showMyReviews(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        Customer customer = customerRepository.findByEmail(userDetails.getUsername());
        model.addAttribute("customerreviews", reviewService.getReviewsByCustomer(customer.getId()));
        return "customer/userreviews";
    }

    @GetMapping("/userreviews/editreview/{id}") // NTD
    public String showEditForm(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model, @PathVariable Long id) {
        
        Customer customer = customerRepository.findByEmail(userDetails.getUsername());
        
        Review review = reviewService.getReviewById(id)
            .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));

        
        if(review.getCustomer().getId() != customer.getId()){
            return "redirect:/customer/403";
        }    

        Listing listing = listingService.getListingById(review.getListing().getListingId())
            .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));

        model.addAttribute("review", review);
        model.addAttribute("listing", listing);        
        return "customer/editreview";
    }

    @PostMapping("/userreviews/editreview/{id}/") // NTD
    public String editMyReview(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model, @PathVariable Long id, Review review) {
        Review updatedReview = reviewService.editReview(id, review);
        
        if (updatedReview != null) {      
           return "redirect:/customer/userreviews";
        } 
        else {
            return "redirect:/customer/userreviews/editreview/" + id + "add?error=true";
        }
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