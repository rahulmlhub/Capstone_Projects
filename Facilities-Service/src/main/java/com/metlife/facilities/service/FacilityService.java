package com.metlife.facilities.service;

/**
 * @author Admin
 */
import com.metlife.facilities.payload.FacilityDTO;

import java.util.List;

public interface FacilityService {
    FacilityDTO createFacility(FacilityDTO facilityDTO);
    FacilityDTO updateFacility(String facilityId, FacilityDTO facilityDTO);
    FacilityDTO getFacilityById(String facilityId);
    List<FacilityDTO> getAllFacilities();
    void deleteFacilityById(String facilityId);
}

