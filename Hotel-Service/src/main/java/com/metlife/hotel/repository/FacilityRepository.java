package com.metlife.hotel.repository;


import com.metlife.hotel.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Admin
 */
public interface FacilityRepository extends JpaRepository<Facility, String> {
}
