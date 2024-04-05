package com.metlife.hotel.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityDTO {

    @NotBlank
    private String facilityId;

    @NotBlank
    private String name;

    private String description;
}
