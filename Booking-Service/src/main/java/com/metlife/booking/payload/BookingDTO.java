package com.metlife.booking.payload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {
    private String bookingId;
    private String guestId;
    private String hotelId;
    private int numberOfGuest;
    private LocalDate checkIn;
    private LocalDate checkOut;
}

