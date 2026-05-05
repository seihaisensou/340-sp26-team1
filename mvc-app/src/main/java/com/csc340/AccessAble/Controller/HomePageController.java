package com.csc340.AccessAble.Controller;
import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomePageController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProviderRepository providerRepository;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails, Model model) {
        model.addAttribute("isCustomer", false);
        model.addAttribute("isProvider", false);  
        
        if(userDetails != null){
            String email = userDetails.getUsername();
            System.out.print(email);
            Customer customer = customerRepository.findByEmail(email);
            Provider provider = providerRepository.findByEmail(email);
            if(customer != null){
                model.addAttribute("isCustomer", true);
            }
            else if(provider != null){
                model.addAttribute("isProvider", true);
            }
        }
        return "provider/home";
    }
}