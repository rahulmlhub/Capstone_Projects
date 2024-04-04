package com.metlife.hotel.payload;

import lombok.Data;

/**
 * @author Admin
 */
@Data
    public class HotelDTO {
        private String hotelId;
        private String name;
        private String address;
        private String phoneNumber;

        // Getters and setters
    }
