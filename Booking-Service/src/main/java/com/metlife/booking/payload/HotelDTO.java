package com.metlife.booking.payload;
/**
 * @author Admin
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {


    private String hotelId;


    private String name;


    private String address;

    private String phoneNumber;

//    private Set<FacilityDTO> facilities;

    public HotelDTO(String hotelId, String name, String address, String phoneNumber) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
