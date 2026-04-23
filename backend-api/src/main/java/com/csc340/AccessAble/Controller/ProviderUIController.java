package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.Listing;
import com.csc340.AccessAble.Service.ListingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/provider")
public class ProviderUIController {

    @Autowired
    private ListingService listingService;

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

    @GetMapping("/test")
public String test() {
    return "test";
}
}