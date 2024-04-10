package com.metlife.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Admin
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String bookingId;
    private String guestId;
    private String hotelId;
    private int numberOfGuest;
    private LocalDate checkIn;
    private LocalDate checkOut;

    // Getters and setters
}

