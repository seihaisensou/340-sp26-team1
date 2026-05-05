package com.csc340.AccessAble.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.csc340.AccessAble.Repository.*;
import com.csc340.AccessAble.Entities.*;

@Service
public class AppUserDetailsService implements UserDetailsService {
  PasswordEncoder passwordEncoder;

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private ProviderRepository providerRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Customer customer = customerRepository.findByEmail(email);
    Provider provider = providerRepository.findByEmail(email);

     if (customer != null) {
      ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
      authList.add(new SimpleGrantedAuthority(customer.getRole()));
      return new org.springframework.security.core.userdetails.User(
      customer.getEmail(), customer.getPassword(), authList);   
    }

    else if(provider != null){
      ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
      authList.add(new SimpleGrantedAuthority(provider.getRole()));
      return new org.springframework.security.core.userdetails.User(
      provider.getEmail(), provider.getPassword(), authList);
    }
    
    else{
      throw new UsernameNotFoundException("User not found with email: " + email);
    }
    
  }
}
