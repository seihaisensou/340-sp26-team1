package com.csc340.AccessAble.Service;

import com.csc340.AccessAble.Entities.Provider;
import com.csc340.AccessAble.Repository.ProviderRepository;
import com.csc340.AccessAble.Entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String UPLOAD_DIR = "src/main/resources/static/providerpfp/";

    public Provider createProvider(Provider provider) {
        provider.setRole("PROVIDER");
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));
        return providerRepository.save(provider);
    }

    public Optional<Provider> getProviderById(Long id) {
        return providerRepository.findById(id);
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Provider getProviderByEmail(String email) {
        return providerRepository.findByEmail(email);
    }

    public Provider updateProvider(Long id, Provider providerDetails) {
        return providerRepository.findById(id).map(provider -> {
            if (providerDetails.getEmail() != null) {
                provider.setEmail(providerDetails.getEmail());
            }
            if (providerDetails.getFirstName() != null) {
                provider.setFirstName(providerDetails.getFirstName());
            }
            if (providerDetails.getLastName() != null) {
                provider.setLastName(providerDetails.getLastName());
            }
            if (providerDetails.getCredentials() != null) {
                provider.setCredentials(providerDetails.getCredentials());
            }
            if (providerDetails.getPassword() != null && !providerDetails.getPassword().isBlank()) {
                provider.setPassword(passwordEncoder.encode(providerDetails.getPassword()));
            }

            return providerRepository.save(provider);
        }).orElseThrow(() -> new RuntimeException("Provider not found"));
    }

    public void saveProfileImage(Provider provider, MultipartFile profileImage) {
        if (profileImage == null || profileImage.isEmpty()) {
            return;
        }

        String originalFileName = profileImage.getOriginalFilename();

        try {
            if (originalFileName != null && originalFileName.contains(".")) {
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                String fileName = String.valueOf(provider.getId()) + "." + fileExtension;

                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                InputStream inputStream = profileImage.getInputStream();

                Files.createDirectories(Paths.get(UPLOAD_DIR));
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

                provider.setProfileImagePath(fileName);
                providerRepository.save(provider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomersByProvider(Long providerId) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        return provider.getCustomers();
    }

    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }

    @Transactional
    public void deleteProviderCascade(Long providerId) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        for (Customer customer : new ArrayList<>(provider.getCustomers())) {
            customer.getProviders().remove(provider);
        }

        provider.getCustomers().clear();

        providerRepository.delete(provider);
    }
}