package com.metlife.booking.payload;

import lombok.Data;

/**
 * @author Admin
 */
@Data
public class GuestDTO {


    private String guestId;
    private String name;
    private String email;
    private String phoneNumber;
    private String aadharNumber;
}

