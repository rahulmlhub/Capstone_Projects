package com.metlife.facilities.repository;

import com.metlife.facilities.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Admin
 */
public interface FacilityRepository extends JpaRepository<Facility, String> {
}
