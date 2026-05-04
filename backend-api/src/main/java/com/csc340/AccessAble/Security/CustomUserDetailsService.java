package com.csc340.AccessAble.Security;

import com.csc340.AccessAble.Entities.Provider;
import com.csc340.AccessAble.Repository.ProviderRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ProviderRepository providerRepository;

    public CustomUserDetailsService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Provider provider = providerRepository.findByEmail(email);

        if (provider == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(
                provider.getEmail(),
                provider.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + provider.getRole()))
        );
    }
}