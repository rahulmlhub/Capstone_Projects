package com.metlife.booking.service;

/**
 * @author Admin
 */
import com.metlife.booking.payload.BookingDTO;
import com.metlife.booking.payload.BookingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(String bookingId, BookingDTO bookingDTO);
    BookingDTO getBookingById(String bookingId);
    List<BookingResponse> getAllBookings();
    void deleteBookingById(String bookingId);
}

