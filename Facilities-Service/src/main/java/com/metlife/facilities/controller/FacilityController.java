package com.metlife.facilities.controller;

import com.metlife.facilities.payload.FacilityDTO;
import com.metlife.facilities.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Admin
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private static final Logger logger = LoggerFactory.getLogger(FacilityController.class);

    private final FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping
    public ResponseEntity<FacilityDTO> createFacility(@RequestBody FacilityDTO facilityDTO) {
        logger.info("Creating a new facility");
        FacilityDTO createdFacility = facilityService.createFacility(facilityDTO);
        logger.info("Facility created successfully with ID: {}", createdFacility.getFacilityId());
        return new ResponseEntity<>(createdFacility, HttpStatus.CREATED);
    }

    @PutMapping("/{facilityId}")
    public ResponseEntity<FacilityDTO> updateFacility(@PathVariable String facilityId, @RequestBody FacilityDTO facilityDTO) {
        logger.info("Updating facility with ID: {}", facilityId);
        FacilityDTO updatedFacility = facilityService.updateFacility(facilityId, facilityDTO);
        logger.info("Facility updated successfully with ID: {}", facilityId);
        return ResponseEntity.ok(updatedFacility);
    }

    @GetMapping("/{facilityId}")
    public ResponseEntity<FacilityDTO> getFacilityById(@PathVariable String facilityId) {
        logger.info("Fetching facility with ID: {}", facilityId);
        FacilityDTO facilityDTO = facilityService.getFacilityById(facilityId);
        logger.info("Facility fetched successfully with ID: {}", facilityId);
        return ResponseEntity.ok(facilityDTO);
    }

    @GetMapping
    public ResponseEntity<List<FacilityDTO>> getAllFacilities() {
        logger.info("Fetching all facilities");
        List<FacilityDTO> facilities = facilityService.getAllFacilities();
        logger.info("Fetched {} facilities", facilities.size());
        return ResponseEntity.ok(facilities);
    }

    @DeleteMapping("/{facilityId}")
    public ResponseEntity<Void> deleteFacilityById(@PathVariable String facilityId) {
        logger.info("Deleting facility with ID: {}", facilityId);
        facilityService.deleteFacilityById(facilityId);
        logger.info("Facility deleted successfully with ID: {}", facilityId);
        return ResponseEntity.noContent().build();
    }
}

