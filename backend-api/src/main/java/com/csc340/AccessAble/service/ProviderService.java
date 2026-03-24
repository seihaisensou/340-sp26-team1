package com.csc340.AccessAble.service;

import org.springframework.stereotype.Service;

import com.csc340.AccessAble.repository.ProviderRepository;
import com.csc340.AccessAble.entity.*;
import com.csc340.AccessAble.repository.*;
import java.util.*;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final ReviewRepository reviewRepository;

    public ProviderService(ProviderRepository providerRepository,
            ReviewRepository reviewRepository) {
        this.providerRepository = providerRepository;
        this.reviewRepository = reviewRepository;
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
        Provider provider = providerRepository.findById(id).get();

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


    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review replyToReview(Long providerId, Long reviewId, String reply) {
        Provider provider = providerRepository.findById(providerId).get();
        Review review = reviewRepository.findById(reviewId).get();

        review.setReply(reply);
        review.setProvider(provider);

        return reviewRepository.save(review);
    }
}