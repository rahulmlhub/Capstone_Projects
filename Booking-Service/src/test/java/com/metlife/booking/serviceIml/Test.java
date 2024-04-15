package com.metlife.booking.serviceIml;

import com.metlife.booking.entity.Booking;
import com.metlife.booking.exception.ResourceNotFoundException;
import com.metlife.booking.payload.BookingDTO;
import com.metlife.booking.payload.BookingResponse;
import com.metlife.booking.payload.GuestDTO;
import com.metlife.booking.payload.HotelDTO;
import com.metlife.booking.repository.BookingRepository;
import com.metlife.booking.proxy.GuestProxy;
import com.metlife.booking.proxy.HotelProxy;
import com.metlife.booking.service.imp.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private GuestProxy guestProxy;

    @Mock
    private HotelProxy hotelProxy;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBooking() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId("1");
        bookingDTO.setGuestId("guest1");
        bookingDTO.setHotelId("hotel1");

        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDTO, booking);

        when(bookingRepository.save(booking)).thenReturn(booking);

        BookingDTO result = bookingService.createBooking(bookingDTO);

        assertEquals(bookingDTO.getBookingId(), result.getBookingId());
        assertEquals(bookingDTO.getGuestId(), result.getGuestId());
        assertEquals(bookingDTO.getHotelId(), result.getHotelId());
    }

    @Test
    void testUpdateBooking() {
        String bookingId = "1";
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(bookingId);

        Booking booking = new Booking();
        booking.setBookingId(bookingId);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(booking)).thenReturn(booking);

        BookingDTO result = bookingService.updateBooking(bookingId, bookingDTO);

        assertEquals(bookingId, result.getBookingId());
    }

    @Test
    void testGetBookingById() {
        String bookingId = "1";
        Booking booking = new Booking();
        booking.setBookingId(bookingId);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        BookingDTO result = bookingService.getBookingById(bookingId);

        assertEquals(bookingId, result.getBookingId());
    }

    @Test
    void testDeleteBookingById() {
        String bookingId = "1";
        Booking booking = new Booking();
        booking.setBookingId(bookingId);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        bookingService.deleteBookingById(bookingId);

        verify(bookingRepository, times(1)).delete(booking);
    }

    @Test
    void testGetAllBookings() {
        Booking booking1 = new Booking();
        booking1.setBookingId("1");
        booking1.setGuestId("guest1");
        booking1.setHotelId("hotel1");

        Booking booking2 = new Booking();
        booking2.setBookingId("2");
        booking2.setGuestId("guest2");
        booking2.setHotelId("hotel2");

        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingRepository.findAll()).thenReturn(bookings);

        HotelDTO hotelDTO1 = new HotelDTO();
        hotelDTO1.setName("Hotel One");
        hotelDTO1.setHotelId("hotel1");

        HotelDTO hotelDTO2 = new HotelDTO();
        hotelDTO2.setName("Hotel Two");
        hotelDTO2.setHotelId("hotel2");

        when(hotelProxy.getHotelById("hotel1")).thenReturn(hotelDTO1);
        when(hotelProxy.getHotelById("hotel2")).thenReturn(hotelDTO2);

        GuestDTO guestDTO1 = new GuestDTO();
        guestDTO1.setName("Guest One");
        guestDTO1.setGuestId("guest1");

        GuestDTO guestDTO2 = new GuestDTO();
        guestDTO2.setName("Guest Two");
        guestDTO2.setGuestId("guest2");

        when(guestProxy.getGuestById("guest1")).thenReturn(guestDTO1);
        when(guestProxy.getGuestById("guest2")).thenReturn(guestDTO2);

        List<BookingResponse> result = bookingService.getAllBookings();

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getBookingId());
        assertEquals("guest1", result.get(0).getGuestId());
        assertEquals("hotel1", result.get(0).getHotelId());
        assertEquals("Guest One", result.get(0).getGuestName());
        assertEquals("Hotel One", result.get(0).getHotelName());

        assertEquals("2", result.get(1).getBookingId());
        assertEquals("guest2", result.get(1).getGuestId());
        assertEquals("hotel2", result.get(1).getHotelId());
        assertEquals("Guest Two", result.get(1).getGuestName());
        assertEquals("Hotel Two", result.get(1).getHotelName());
    }
}
