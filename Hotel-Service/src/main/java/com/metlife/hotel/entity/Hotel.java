package com.metlife.hotel.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "phone_number")
    private String phoneNumber;

//    @Column(name = "image_path")
//    private String imagePath;

    @ManyToMany
    @JoinTable(name = "hotel_facility",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private Set<Facility> facilities = new HashSet<>();



    // Add utility methods to add/remove facilities
    public void addFacility(Facility facility) {
        this.facilities.add(facility);
        facility.getHotels().add(this);
    }

    public void removeFacility(Facility facility) {
        this.facilities.remove(facility);
        facility.getHotels().remove(this);
    }

    public Hotel(String hotelId, String name, String address, String phoneNumber) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

