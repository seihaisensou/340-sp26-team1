package com.csc340.AccessAble.Service;

import com.csc340.AccessAble.Entities.*;
import com.csc340.AccessAble.Entities.Booking.BookingStatus;
import com.csc340.AccessAble.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ListingRepository listingRepository;
    private final CustomerRepository customerRepository;
    private final ProviderRepository providerRepository;

    public BookingService(
            BookingRepository bookingRepository,
            ListingRepository listingRepository,
            CustomerRepository customerRepository,
            ProviderRepository providerRepository) {

        this.bookingRepository = bookingRepository;
        this.listingRepository = listingRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
    }

    public Booking createBooking(Booking booking, long customerId, long listingId) {

        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        booking.setListing(listing);
        booking.setCustomer(customer);
        booking.setStatus(BookingStatus.PENDING);

        Provider provider = listing.getProvider();

        if (!customer.getProviders().contains(provider)) {
            customer.getProviders().add(provider);
        }

        if (!provider.getCustomers().contains(customer)) {
            provider.getCustomers().add(customer);
        }

        customerRepository.save(customer);
        providerRepository.save(provider);

        return bookingRepository.save(booking);
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingByCustomerId(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setStatus(bookingDetails.getStatus());
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}