package com.csc340.AccessAble.Service;

import org.springframework.stereotype.Service;

import com.csc340.AccessAble.Entities.Provider;
import com.csc340.AccessAble.Repository.ProviderRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider createProvider(Provider provider) {
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

    public List<String> getCustomersByProvider(Long providerId) {
    //placehold
    return List.of("customer1@example.com", "customer2@example.com");
}
}