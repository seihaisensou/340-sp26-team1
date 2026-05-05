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

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Customer customer = customerRepository.findByEmail(email);

     if (customer == null) {
      throw new UsernameNotFoundException("User not found with email: " + email);
    }

    ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
    authList.add(new SimpleGrantedAuthority(customer.getRole()));
    return new org.springframework.security.core.userdetails.User(
    customer.getEmail(), customer.getPassword(), authList);   
  }
}
