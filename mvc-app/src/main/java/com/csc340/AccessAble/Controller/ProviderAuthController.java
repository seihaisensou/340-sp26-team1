package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.Provider;
import com.csc340.AccessAble.Repository.ProviderRepository;
import com.csc340.AccessAble.Service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/provider")
public class ProviderAuthController {

    private final ProviderService providerService;
    private final ProviderRepository providerRepository;

    public ProviderAuthController(
            ProviderService providerService,
            ProviderRepository providerRepository) {
        this.providerService = providerService;
        this.providerRepository = providerRepository;
    }

    @GetMapping("/sign-up")
    public String showSignup(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider/sign-up";
    }

    @GetMapping("/login")
    public String showLogin(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "deleted", required = false) String deleted,
            Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        }

        if (deleted != null) {
            model.addAttribute("deleted", true);
        }

        return "provider/login";
    }

    @PostMapping("/signup")
    public String signup(
            Provider provider,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Model model) {

        try {
            if (provider.getEmail() == null || provider.getEmail().isBlank()) {
                model.addAttribute("error", "Email is required");
                return "provider/sign-up";
            }

            if (providerRepository.findByEmail(provider.getEmail()) != null) {
                model.addAttribute("error", "Email already exists");
                return "provider/sign-up";
            }

            if (provider.getPassword() == null || provider.getPassword().length() < 6) {
                model.addAttribute("error", "Password must be at least 6 characters");
                return "provider/sign-up";
            }

            provider.setRole("PROVIDER");

            Provider savedProvider = providerService.createProvider(provider);

            if (file != null && !file.isEmpty()) {
                providerService.saveProfileImage(savedProvider, file);
            } else {
                savedProvider.setProfileImagePath("default.png");
            }

            return "redirect:/provider/login";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Signup failed: " + e.getMessage());
            return "provider/sign-up";
        }
    }
}