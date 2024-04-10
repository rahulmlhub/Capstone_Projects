package com.metlife.booking.payload;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Getter
public class BookingResponse {

        private String bookingId;
        private String guestId;
        private String hotelId;
        private int numberOfGuest;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private String hotelName;
        private String guestName;

}
