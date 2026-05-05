package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.Provider;
import com.csc340.AccessAble.Service.ProviderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/provider")
public class ProviderAuthController {

    private final ProviderService providerService;
    private final PasswordEncoder passwordEncoder;

    public ProviderAuthController(ProviderService providerService, PasswordEncoder passwordEncoder) {
        this.providerService = providerService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignup(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider/sign-up";
    }

    @GetMapping("/login")
    public String showLogin() {
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

            if (providerService.findByEmail(provider.getEmail()) != null) {
                model.addAttribute("error", "Email already exists");
                return "provider/sign-up";
            }

            if (provider.getPassword() == null || provider.getPassword().length() < 6) {
                model.addAttribute("error", "Password must be at least 6 characters");
                return "provider/sign-up";
            }

            if (file != null && !file.isEmpty()) {
                String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path uploadDir = Paths.get("uploads");

                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path filePath = uploadDir.resolve(filename);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                provider.setProfileImagePath("/uploads/" + filename);
            } else {
                provider.setProfileImagePath("/images/default.png");
            }
            provider.setRole("PROVIDER");
            provider.setPassword(passwordEncoder.encode(provider.getPassword()));

            providerService.saveProvider(provider);

            return "redirect:/provider/account";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Signup failed: " + e.getMessage());
            return "provider/sign-up";
        }
    }
}