package com.csc340.AccessAble.Security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.csc340.AccessAble.Repository.ProviderRepository;
import com.csc340.AccessAble.Entities.Provider;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ProviderRepository providerRepository;

    public CustomUserDetailsService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) {
        Provider provider = providerRepository.findByEmail(email);

        if (provider == null) {
            throw new RuntimeException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                provider.getEmail(),
                provider.getPassword(),
                java.util.List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority(provider.getRole()))
        );
    }
}