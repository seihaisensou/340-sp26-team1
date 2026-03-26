package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.Booking;
import com.csc340.AccessAble.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/customer/{customerId}/listing/{listingId}")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @PathVariable long customerId, @PathVariable long listingId) {
        Booking createdBooking = bookingService.createBooking(booking, customerId, listingId);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking() {
        List<Booking> booking = bookingService.getAllBooking();
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Booking>> getBookingByCustomerId(@PathVariable Long customerId) {
        List<Booking> bookings = bookingService.getBookingByCustomerId(customerId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}