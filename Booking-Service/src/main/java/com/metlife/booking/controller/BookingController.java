package com.metlife.booking.controller;

import com.metlife.booking.payload.BookingDTO;
import com.metlife.booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        logger.info("Creating a new booking");
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        logger.info("Booking created successfully with ID: {}", createdBooking.getBookingId());
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable String bookingId, @RequestBody BookingDTO bookingDTO) {
        logger.info("Updating booking with ID: {}", bookingId);
        BookingDTO updatedBooking = bookingService.updateBooking(bookingId, bookingDTO);
        logger.info("Booking updated successfully with ID: {}", bookingId);
        return ResponseEntity.ok(updatedBooking);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable String bookingId) {
        logger.info("Fetching booking with ID: {}", bookingId);
        BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
        logger.info("Booking fetched successfully with ID: {}", bookingId);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        logger.info("Fetching all bookings");
        List<BookingDTO> bookings = bookingService.getAllBookings();
        logger.info("Fetched {} bookings", bookings.size());
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable String bookingId) {
        logger.info("Deleting booking with ID: {}", bookingId);
        bookingService.deleteBookingById(bookingId);
        logger.info("Booking deleted successfully with ID: {}", bookingId);
        return ResponseEntity.noContent().build();
    }
}

