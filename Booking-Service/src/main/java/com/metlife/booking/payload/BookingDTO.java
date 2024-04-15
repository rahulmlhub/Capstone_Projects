package com.metlife.booking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String bookingId;
    private String guestId;
    private String hotelId;
    private int numberOfGuest;
    private LocalDate checkIn;
    private LocalDate checkOut;


}

