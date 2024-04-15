package com.metlife.hotel.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.metlife.hotel.payload.HotelDTO;
import com.metlife.hotel.service.HotelService;

import java.util.List;

/**
 * @author Admin
 */
@RestController
@RequestMapping("/api/hotels")
//@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/create")
    public ResponseEntity<HotelDTO> createHotel(@Valid @RequestBody HotelDTO hotelDTO) {
        logger.info("Creating a new hotel");
        HotelDTO createdHotel = hotelService.createHotel(hotelDTO);
        logger.info("Hotel created successfully with ID: {}", createdHotel.getHotelId());
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @PutMapping("/update/{hotelId}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable String hotelId, @Valid @RequestBody HotelDTO hotelDTO) {
        logger.info("Updating hotel with ID: {}", hotelId);
        HotelDTO updatedHotel = hotelService.updateHotel(hotelId, hotelDTO);
        logger.info("Hotel updated successfully with ID: {}", hotelId);
        return ResponseEntity.ok(updatedHotel);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable String hotelId) {
        logger.info("Fetching hotel with ID: {}", hotelId);
        HotelDTO hotelDTO = hotelService.getHotelById(hotelId);
        logger.info("Hotel fetched successfully with ID: {}", hotelId);
        return ResponseEntity.ok(hotelDTO);
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        logger.info("Fetching all hotels");
        List<HotelDTO> hotels = hotelService.getAllHotel();
        logger.info("Fetched {} hotels", hotels.size());
        return ResponseEntity.ok(hotels);
    }

    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable String hotelId) {
        logger.info("Deleting hotel with ID: {}", hotelId);
        hotelService.deleteHotelById(hotelId);
        logger.info("Hotel deleted successfully with ID: {}", hotelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/withFacilities")
    public ResponseEntity<List<HotelDTO>> getAllHotelsWithFacilities() {
        List<HotelDTO> hotelDTOs = hotelService.getAllHotelWithFacility();
        return new ResponseEntity<>(hotelDTOs, HttpStatus.OK);
    }
}
