package com.metlife.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    private String bookingId;
    private String guestId;
    private String hotelId;
    private LocalDate checkIn;
    private LocalDate checkOut;

    // Getters and setters
}

