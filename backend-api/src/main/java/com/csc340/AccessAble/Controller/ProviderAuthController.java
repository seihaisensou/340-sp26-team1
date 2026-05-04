package com.csc340.AccessAble.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.AccessAble.Entities.Provider;

import com.csc340.AccessAble.Service.ProviderService;

@Controller
@RequestMapping("/provider")
public class ProviderAuthController {

    private final ProviderService providerService;

    public ProviderAuthController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/sign-up")
    public String showSignup() {
        return "provider/sign-up";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "provider/login";
    }

    @PostMapping("/sign-up")
    public String signup(@RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {

        Provider provider = new Provider();
        provider.setFirstName(firstName);
        provider.setLastName(lastName);
        provider.setEmail(email);
        provider.setPassword(password);
        provider.setRole("PROVIDER");

        providerService.createProvider(provider);

        return "redirect:/provider/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            Model model) {

        Provider provider = providerService.login(email, password);

        if (provider == null) {
            model.addAttribute("error", "Invalid Login");
            return "provider/login";
        }

        return "redirect:/provider/account";
    }
}