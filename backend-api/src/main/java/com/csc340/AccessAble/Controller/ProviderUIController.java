package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.Listing;
import com.csc340.AccessAble.Entities.Provider;
import com.csc340.AccessAble.Repository.ProviderRepository;
import com.csc340.AccessAble.Service.ListingService;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/provider")
public class ProviderUIController {

    private final ListingService listingService;
    private final ProviderRepository providerRepository;

    public ProviderUIController(ListingService listingService, ProviderRepository providerRepository) {
        this.listingService = listingService;
        this.providerRepository = providerRepository;
    }

    @GetMapping("/create")
    public String showCreatePage() {
        return "provider/p-create-list";
    }

    @PostMapping("/create")
    public String createListing(Listing listing) {
        listingService.saveListing(listing);
        return "redirect:/provider/my-listings";
    }

    @GetMapping("/my-listings")
    public String showListings(Model model) {
        model.addAttribute("listings", listingService.getAllListings());
        return "provider/my-listings";
    }

    @GetMapping("/account")
    public String accountPage(Model model, Principal principal) {

        String email = principal.getName();

        Provider provider = providerRepository.findByEmail(email);

        model.addAttribute("provider", provider);

        return "provider/account";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Long id, Model model) {

        Listing listing = listingService.getListingById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));

        model.addAttribute("listing", listing);
        return "provider/edit-list";
    }

    @PostMapping("/update/{id}")
    public String updateListing(@PathVariable Long id, Listing updated) {
        listingService.updateListing(id, updated);
        return "redirect:/provider/my-listings";
    }

    @GetMapping("/delete/{id}")
    public String deleteListing(@PathVariable Long id) {
        listingService.deleteListing(id);
        return "redirect:/provider/my-listings";
    }
}