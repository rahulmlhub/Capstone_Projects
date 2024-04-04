package com.metlife.facilities.service.imp;


import com.metlife.facilities.entity.Facility;
import com.metlife.facilities.exception.ResourceNotFoundException;
import com.metlife.facilities.payload.FacilityDTO;
import com.metlife.facilities.repository.FacilityRepository;
import com.metlife.facilities.service.FacilityService;

/**
 * @author Admin
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityServiceImpl implements FacilityService {

    private static final Logger logger = LoggerFactory.getLogger(FacilityServiceImpl.class);

    private final FacilityRepository facilityRepository;

    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public FacilityDTO createFacility(FacilityDTO facilityDTO) {
        logger.info("Creating a new facility");
        Facility facility = new Facility();
        BeanUtils.copyProperties(facilityDTO, facility);
        Facility savedFacility = facilityRepository.save(facility);
        FacilityDTO savedFacilityDTO = new FacilityDTO();
        BeanUtils.copyProperties(savedFacility, savedFacilityDTO);
        logger.info("Facility created successfully with ID: {}", savedFacilityDTO.getFacilityId());
        return savedFacilityDTO;
    }

    @Override
    public FacilityDTO updateFacility(String facilityId, FacilityDTO facilityDTO) {
        logger.info("Updating facility with ID: {}", facilityId);
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Facility ","ID" ,facilityId));
        BeanUtils.copyProperties(facilityDTO, facility);
        Facility updatedFacility = facilityRepository.save(facility);
        FacilityDTO updatedFacilityDTO = new FacilityDTO();
        BeanUtils.copyProperties(updatedFacility, updatedFacilityDTO);
        logger.info("Facility updated successfully with ID: {}", facilityId);
        return updatedFacilityDTO;
    }

    @Override
    public FacilityDTO getFacilityById(String facilityId) {
        logger.info("Fetching facility with ID: {}", facilityId);
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Facility ","ID" ,facilityId));
        FacilityDTO facilityDTO = new FacilityDTO();
        BeanUtils.copyProperties(facility, facilityDTO);
        logger.info("Facility fetched successfully with ID: {}", facilityId);
        return facilityDTO;
    }

    @Override
    public List<FacilityDTO> getAllFacilities() {
        logger.info("Fetching all facilities");
        List<Facility> facilities = facilityRepository.findAll();
        List<FacilityDTO> facilityDTOs = facilities.stream()
                .map(facility -> {
                    FacilityDTO facilityDTO = new FacilityDTO();
                    BeanUtils.copyProperties(facility, facilityDTO);
                    return facilityDTO;
                })
                .collect(Collectors.toList());
        logger.info("Fetched {} facilities", facilityDTOs.size());
        return facilityDTOs;
    }

    @Override
    public void deleteFacilityById(String facilityId) {
        logger.info("Deleting facility with ID: {}", facilityId);
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Facility ","ID" ,facilityId));
        facilityRepository.delete(facility);
        logger.info("Facility deleted successfully with ID: {}", facilityId);
    }
}

