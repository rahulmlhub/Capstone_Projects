package com.metlife.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Admin
 */
@Data
@NoArgsConstructor
public class GuestDTO {


    private String guestId;
    private String name;
    private String email;
    private String phoneNumber;
    private String aadharNumber;
}

