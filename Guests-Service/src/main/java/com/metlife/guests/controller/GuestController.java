package com.metlife.guests.controller;

import com.metlife.guests.payload.GuestDTO;
import com.metlife.guests.service.GuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private static final Logger logger = LoggerFactory.getLogger(GuestController.class);

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<GuestDTO> createGuest(@RequestBody GuestDTO guestDTO) {
        logger.info("Creating a new guest");
        GuestDTO createdGuest = guestService.createGuest(guestDTO);
        logger.info("Guest created successfully with ID: {}", createdGuest.getGuestId());
        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<GuestDTO> updateGuest(@PathVariable String guestId, @RequestBody GuestDTO guestDTO) {
        logger.info("Updating guest with ID: {}", guestId);
        GuestDTO updatedGuest = guestService.updateGuest(guestId, guestDTO);
        logger.info("Guest updated successfully with ID: {}", guestId);
        return ResponseEntity.ok(updatedGuest);
    }

    @GetMapping("/{guestId}")
    public ResponseEntity<GuestDTO> getGuestById(@PathVariable String guestId) {
        logger.info("Fetching guest with ID: {}", guestId);
        GuestDTO guestDTO = guestService.getGuestById(guestId);
        logger.info("Guest fetched successfully with ID: {}", guestId);
        return ResponseEntity.ok(guestDTO);
    }

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        logger.info("Fetching all guests");
        List<GuestDTO> guests = guestService.getAllGuests();
        logger.info("Fetched {} guests", guests.size());
        return ResponseEntity.ok(guests);
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<Void> deleteGuestById(@PathVariable String guestId) {
        logger.info("Deleting guest with ID: {}", guestId);
        guestService.deleteGuestById(guestId);
        logger.info("Guest deleted successfully with ID: {}", guestId);
        return ResponseEntity.noContent().build();
    }
}

