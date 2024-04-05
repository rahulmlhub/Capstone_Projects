package com.metlife.hotel.repository;

import com.metlife.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Admin
 */
public interface HotelRepository extends JpaRepository<Hotel, String> {
    @Query("SELECT DISTINCT h FROM Hotel h LEFT JOIN FETCH h.facilities")
    List<Hotel> findAllWithFacilities();
}
