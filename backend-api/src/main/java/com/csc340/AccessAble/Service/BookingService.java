package com.csc340.AccessAble.Service;

import com.csc340.AccessAble.Entities.Booking;
import com.csc340.AccessAble.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    public Booking createBooking(Booking booking) {
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

    public Booking startBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setStatus(bookingDetails.getStatus());
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }
    
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}