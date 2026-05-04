package com.csc340.AccessAble.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.csc340.AccessAble.Entities.Provider;

import com.csc340.AccessAble.Service.ProviderService;

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
    public String showSignup() {
        return "provider/sign-up";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "provider/login";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {

        Provider provider = new Provider();
        provider.setFirstName(firstName);
        provider.setLastName(lastName);
        provider.setEmail(email);
        provider.setPassword(passwordEncoder.encode(password));
        provider.setRole("PROVIDER");

        providerService.createProvider(provider);

        return "redirect:/provider/login";
    }

}