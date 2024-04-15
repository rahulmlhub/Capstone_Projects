package com.metlife.booking.repository;

import com.metlife.booking.entity.Booking;
import com.metlife.booking.payload.BookingDTO;
import com.metlife.booking.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingRepositoryTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBooking() {
        BookingDTO bookingDTO = new BookingDTO(
                "1",
                "guest1",
                "hotel1",
                2,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        Booking booking = new Booking(
                "1",
                "guest1",
                "hotel1",
                2,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        BookingDTO savedBooking = bookingService.createBooking(bookingDTO);

        assertNotNull(savedBooking);
        assertEquals("1", savedBooking.getBookingId());
        assertEquals("guest1", savedBooking.getGuestId());
        assertEquals("hotel1", savedBooking.getHotelId());
        assertEquals(2, savedBooking.getNumberOfGuest());
    }

    @Test
    void testFindBookingById() {
        Booking booking = new Booking(
                "1",
                "guest1",
                "hotel1",
                2,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        when(bookingRepository.findById("1")).thenReturn(Optional.of(booking));

        BookingDTO foundBooking = bookingService.getBookingById("1");

        assertNotNull(foundBooking);
        assertEquals("1", foundBooking.getBookingId());
    }

    @Test
    void testUpdateBooking() {
        BookingDTO bookingDTO = new BookingDTO(
                "1",
                "guest1",
                "hotel1",
                2,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        Booking booking = new Booking(
                "1",
                "guest1",
                "hotel1",
                2,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        when(bookingRepository.findById("1")).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingDTO updatedBooking = bookingService.updateBooking("1", bookingDTO);

        assertNotNull(updatedBooking);
        assertEquals("1", updatedBooking.getBookingId());
        assertEquals("guest1", updatedBooking.getGuestId());
        assertEquals("hotel1", updatedBooking.getHotelId());
        assertEquals(2, updatedBooking.getNumberOfGuest());
    }

    @Test
    void testDeleteBooking() {
        doNothing().when(bookingRepository).deleteById("1");

        bookingService.deleteBookingById("1");

        verify(bookingRepository, times(1)).deleteById("1");
    }
}
