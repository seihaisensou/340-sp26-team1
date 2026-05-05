package com.csc340.AccessAble.Controller;
import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Repository.*;
import com.csc340.AccessAble.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerAuthController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/sign-up")
    public String showSignup(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/sign-up";
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

        return "customer/login";
    }

    @PostMapping("/sign-up/")
    public String signup(
            Customer customer, Model model) {

        try {
            if (customer.getEmail() == null || customer.getEmail().isBlank()) {
                model.addAttribute("error", "Email is required");
                return "customer/sign-up";
            }

            if (customerRepository.findByEmail(customer.getEmail()) != null) {
                model.addAttribute("error", "Email already exists");
                return "customer/sign-up";
            }

            if (customer.getPassword() == null || customer.getPassword().length() < 6) {
                model.addAttribute("error", "Password must be at least 6 characters");
                return "customer/sign-up";
            }

            customer.setRole("CUSTOMER");

            customerService.createCustomer(customer);           

            return "redirect:/customer/login";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Signup failed: " + e.getMessage());
            return "customer/sign-up";
        }
    }
}