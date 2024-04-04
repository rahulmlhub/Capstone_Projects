package com.metlife.hotel.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Admin
 */
@Data
    public class HotelDTO {

        private String hotelId;
        @NotNull
        private String name;
         @NotNull
        private String address;
        @NotNull
        private String phoneNumber;

        // Getters and setters
    }
