package com.metlife.hotel.repository;

import com.metlife.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Admin
 */
public interface HotelRepository extends JpaRepository<Hotel, String> {
}
