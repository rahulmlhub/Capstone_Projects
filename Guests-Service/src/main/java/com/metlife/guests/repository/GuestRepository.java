package com.metlife.guests.repository;

import com.metlife.guests.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Admin
 */
public interface GuestRepository extends JpaRepository<Guest, String> {
}
