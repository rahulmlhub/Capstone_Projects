package com.metlife.booking.controller;


import com.metlife.booking.payload.BookingDTO;
import com.metlife.booking.payload.BookingResponse;
import com.metlife.booking.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBooking() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId("1");
        when(bookingService.createBooking(bookingDTO)).thenReturn(bookingDTO);

        ResponseEntity<BookingDTO> response = bookingController.createBooking(bookingDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bookingDTO, response.getBody());
    }

    @Test
    void testUpdateBooking() {
        String bookingId = "1";
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(bookingId);
        when(bookingService.updateBooking(bookingId, bookingDTO)).thenReturn(bookingDTO);

        ResponseEntity<BookingDTO> response = bookingController.updateBooking(bookingId, bookingDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookingDTO, response.getBody());
    }

    @Test
    void testGetBookingById() {
        String bookingId = "1";
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(bookingId);
        when(bookingService.getBookingById(bookingId)).thenReturn(bookingDTO);

        ResponseEntity<BookingDTO> response = bookingController.getBookingById(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookingDTO, response.getBody());
    }

    @Test
    void testGetAllBookings() {
        BookingResponse bookingResponse1 = new BookingResponse();
        bookingResponse1.setBookingId("1");
        BookingResponse bookingResponse2 = new BookingResponse();
        bookingResponse2.setBookingId("2");

        List<BookingResponse> bookingResponses = Arrays.asList(bookingResponse1, bookingResponse2);
        when(bookingService.getAllBookings()).thenReturn(bookingResponses);

        ResponseEntity<List<BookingResponse>> response = bookingController.getAllBookings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookingResponses, response.getBody());
    }

    @Test
    void testDeleteBookingById() {
        String bookingId = "1";
        ResponseEntity<String> response = bookingController.deleteBookingById(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Booking deleted successfully with ID:" + bookingId, response.getBody());

        verify(bookingService, times(1)).deleteBookingById(bookingId);
    }
}

