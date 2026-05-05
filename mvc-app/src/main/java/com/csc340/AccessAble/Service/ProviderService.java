package com.csc340.AccessAble.Service;

import org.springframework.stereotype.Service;

import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Repository.*;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final CustomerRepository customerRepository;

    public ProviderService(ProviderRepository providerRepository, CustomerRepository customerRepository) {
        this.providerRepository = providerRepository;
        this.customerRepository = customerRepository;
    }

    public Provider createProvider(Provider provider) {
        provider.setRole("PROVIDER");
        return providerRepository.save(provider);
    }

    public Optional<Provider> getProviderById(Long id) {
        return providerRepository.findById(id);
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Provider updateProvider(Long id, Provider updated) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found with id " + id));

        if (updated.getFirstName() != null)
            provider.setFirstName(updated.getFirstName());

        if (updated.getLastName() != null)
            provider.setLastName(updated.getLastName());

        if (updated.getCredentials() != null)
            provider.setCredentials(updated.getCredentials());

        if (updated.getEmail() != null)
            provider.setEmail(updated.getEmail());

        if (updated.getPassword() != null)
            provider.setPassword(updated.getPassword());

        return providerRepository.save(provider);
    }

    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }

    public List<Customer> getCustomersByProvider(Long providerId) {
        return customerRepository.findByProviderId(providerId);
    }

    public Provider login(String email, String password) {
        Provider provider = providerRepository.findByEmail(email);

        if (provider != null && provider.getPassword().equals(password)) {
            return provider;
        }
        return null;
    }

}
