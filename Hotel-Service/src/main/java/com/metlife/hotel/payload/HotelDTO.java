package com.metlife.hotel.payload;
/**
 * @author Admin
 */
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

//    @NotBlank
    private String hotelId;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    private String phoneNumber;

    private Set<FacilityDTO> facilities;

    public HotelDTO(String hotelId, String name, String address, String phoneNumber) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
