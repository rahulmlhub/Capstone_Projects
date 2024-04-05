package com.metlife.booking.repository;

import com.metlife.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Admin
 */
public interface BookingRepository extends JpaRepository<Booking, String> {
}
