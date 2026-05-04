package com.csc340.AccessAble.Security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import com.csc340.AccessAble.Entities.Provider;
import com.csc340.AccessAble.Repository.ProviderRepository;

public class CustomProviderDetailsService implements UserDetailsService {
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Provider provider = providerRepository.findByEmail(email);

        if (provider == null) {
            throw new UsernameNotFoundException("Provider not found");
        }

        ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(provider.getRole()));

        return new org.springframework.security.core.userdetails.User(
                provider.getEmail(),
                provider.getPassword(),
                authList);
    }

}
