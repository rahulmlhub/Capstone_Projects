package com.metlife.hotel.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Admin
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rating")
    private Double rating;
}
