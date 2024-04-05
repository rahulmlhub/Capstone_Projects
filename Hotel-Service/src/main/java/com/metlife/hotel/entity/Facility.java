package com.metlife.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Facility {

    @Id
    @Column(name = "facility_id")
    private String facilityId;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "facilities")
    private Set<Hotel> hotels = new HashSet<>();

    public Facility(String facilityId, String name) {
        this.facilityId = facilityId;
        this.name = name;
//        this.description = description;
    }
}
