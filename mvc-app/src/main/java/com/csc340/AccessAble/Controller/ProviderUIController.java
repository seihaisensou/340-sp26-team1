package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Repository.*;
import com.csc340.AccessAble.Service.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;



@Controller
@RequestMapping("/provider")
public class ProviderUIController {

    private final ListingService listingService;
    private final ProviderRepository providerRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookingService bookingService;
    private final ReviewService reviewService;

    public ProviderUIController(
            ListingService listingService,
            ProviderRepository providerRepository,
            PasswordEncoder passwordEncoder,
            BookingService bookingService,
            ReviewService reviewService) {

        this.listingService = listingService;
        this.providerRepository = providerRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookingService = bookingService;
        this.reviewService = reviewService;
    }

    @GetMapping("/create")
    public String showCreatePage(Model model, Principal principal) {

        if (principal != null) {
            Provider provider = providerRepository.findByEmail(principal.getName());
            model.addAttribute("provider", provider);
        }

        return "provider/p-create-list";
    }

    @PostMapping("/create")
    public String createListing(Listing listing, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        Provider provider = providerRepository.findByEmail(userDetails.getUsername());
        listing.setProvider(provider);
        listingService.saveListing(listing);
        return "redirect:/provider/my-listings";
    }

    @GetMapping("/my-listings")
    public String showListings(Model model, Principal principal) {

        if (principal != null) {
            Provider provider = providerRepository.findByEmail(principal.getName());
            model.addAttribute("provider", provider);
        }

        model.addAttribute("listings", listingService.getAllListings());
        return "provider/my-listings";
    }

    @GetMapping("/account")
    public String accountPage(Model model, Principal principal) {

        if (principal == null) {
            return "redirect:/provider/login";
        }

        Provider provider = providerRepository.findByEmail(principal.getName());

        if (provider == null) {
            return "redirect:/provider/login";
        }

        model.addAttribute("isLoggedIn", true);
        model.addAttribute("provider", provider);

        return "provider/account";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Long id, Model model) {

        Listing listing = listingService.getListingById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        model.addAttribute("listing", listing);
        return "provider/edit-list";
    }

    @PostMapping("/update/{id}")
    public String updateListing(@PathVariable Long id, Listing updated) {
        listingService.updateListing(id, updated);
        return "redirect:/provider/my-listings";
    }

    @PostMapping("/update")
    public String updateProvider(Provider updated, Principal principal) {

        if (principal == null) {
            return "redirect:/provider/login";
        }

        Provider existing = providerRepository.findByEmail(principal.getName());

        if (existing == null) {
            return "redirect:/provider/login";
        }

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setCredentials(updated.getCredentials());

        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(updated.getPassword()));
        }

        providerRepository.save(existing);

        return "redirect:/provider/account";
    }

    @PostMapping("/upload-image")
    public String uploadImage(@RequestParam("file") MultipartFile file,
            Principal principal) {

        if (principal == null) {
            return "redirect:/provider/login";
        }

        Provider provider = providerRepository.findByEmail(principal.getName());

        try {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path uploadDir = Paths.get("uploads");

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = uploadDir.resolve(filename);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            provider.setProfileImagePath(filename);
            providerRepository.save(provider);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/provider/account";
    }

    @PostMapping("/booking/update/{id}")
    public String updateBookingStatus(@PathVariable Long id,
            @RequestParam("status") Booking.BookingStatus status) {

        Booking booking = bookingService.getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);
        bookingService.updateBooking(id, booking);

        return "redirect:/provider/current-cust";
    }

    @GetMapping("/current-cust")
    public String viewCustomers(Model model, Principal principal) {

        if (principal == null) {
            return "redirect:/provider/login";
        }

        model.addAttribute("isLoggedIn", true);
        model.addAttribute("bookings", bookingService.getAllBooking());

        return "provider/current-cust";
    }

    @GetMapping("/delete/{id}")
    public String deleteListing(@PathVariable Long id) {
        listingService.deleteListing(id);
        return "redirect:/provider/my-listings";
    }

    @GetMapping("/reviews")
    public String viewReviews(Model model, Principal principal) {

        if (principal == null) {
            return "redirect:/provider/login";
        }

        Provider provider = providerRepository.findByEmail(principal.getName());

        List<Review> reviews = reviewService.getReviewsByProvider(provider.getId());

        model.addAttribute("reviews", reviews);
        model.addAttribute("isLoggedIn", true);

        return "provider/p-reviews";
    }

    @PostMapping("/review/reply/{reviewId}")
    public String replyToReview(
            @PathVariable Long reviewId,
            @RequestParam String reply,
            Principal principal) {

        reviewService.replyToReview(reviewId, reply);

        return "redirect:/provider/reviews";
    }

    @GetMapping("/reviews/editreply/{reviewId}")
    public String editReviewReply(Model model, Principal principal, @PathVariable Long reviewId) {

        if (principal == null) {
            return "redirect:/provider/login";
        }

        Provider provider = providerRepository.findByEmail(principal.getName());
        
        Review review = reviewService.getReviewById(reviewId)
        .orElseThrow(() -> new RuntimeException("Booking not found")); 

        if(review.getProvider().getId() != provider.getId()){
            return "redirect:/provider/403";
        }

        Listing listing = listingService.getListingById(review.getListing().getListingId())
            .orElseThrow(() -> new RuntimeException("Listing not found"));


        model.addAttribute("review", review);
        model.addAttribute("listing", listing);
        model.addAttribute("isLoggedIn", true);

        return "provider/edit-reply";
    }

    @PostMapping("/reviews/editreply/{reviewId}/") 
    public String editMyReview(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model, @PathVariable Long reviewId, Review review) {
        Review updatedReview = reviewService.editReview(reviewId, review);
        
        if (updatedReview != null) {      
           return "redirect:/provider/reviews";
        } 
        else {
            return "redirect:/provider/reviews/editreview/" + reviewId + "add?error=true";
        }
    }
}