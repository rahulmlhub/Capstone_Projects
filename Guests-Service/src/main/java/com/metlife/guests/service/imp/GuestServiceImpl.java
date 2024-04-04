package com.metlife.guests.service.imp;

import com.metlife.guests.entity.Guest;
import com.metlife.guests.exception.ResourceNotFoundException;
import com.metlife.guests.payload.GuestDTO;
import com.metlife.guests.repository.GuestRepository;
import com.metlife.guests.service.GuestService;

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
public class GuestServiceImpl implements GuestService {

    private static final Logger logger = LoggerFactory.getLogger(GuestServiceImpl.class);

    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public GuestDTO createGuest(GuestDTO guestDTO) {
        logger.info("Creating a new guest");
        Guest guest = new Guest();
        BeanUtils.copyProperties(guestDTO, guest);
        Guest savedGuest = guestRepository.save(guest);
        GuestDTO savedGuestDTO = new GuestDTO();
        BeanUtils.copyProperties(savedGuest, savedGuestDTO);
        logger.info("Guest created successfully with ID: {}", savedGuestDTO.getGuestId());
        return savedGuestDTO;
    }

    @Override
    public GuestDTO updateGuest(String guestId, GuestDTO guestDTO) {
        logger.info("Updating guest with ID: {}", guestId);
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest","ID", guestId));
        BeanUtils.copyProperties(guestDTO, guest);
        Guest updatedGuest = guestRepository.save(guest);
        GuestDTO updatedGuestDTO = new GuestDTO();
        BeanUtils.copyProperties(updatedGuest, updatedGuestDTO);
        logger.info("Guest updated successfully with ID: {}", guestId);
        return updatedGuestDTO;
    }

    @Override
    public GuestDTO getGuestById(String guestId) {
        logger.info("Fetching guest with ID: {}", guestId);
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest","ID", guestId));
        GuestDTO guestDTO = new GuestDTO();
        BeanUtils.copyProperties(guest, guestDTO);
        logger.info("Guest fetched successfully with ID: {}", guestId);
        return guestDTO;
    }

    @Override
    public List<GuestDTO> getAllGuests() {
        logger.info("Fetching all guests");
        List<Guest> guests = guestRepository.findAll();
        List<GuestDTO> guestDTOs = guests.stream()
                .map(guest -> {
                    GuestDTO guestDTO = new GuestDTO();
                    BeanUtils.copyProperties(guest, guestDTO);
                    return guestDTO;
                })
                .collect(Collectors.toList());
        logger.info("Fetched {} guests", guestDTOs.size());
        return guestDTOs;
    }

    @Override
    public void deleteGuestById(String guestId) {
        logger.info("Deleting guest with ID: {}", guestId);
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest","ID", guestId));
        guestRepository.delete(guest);
        logger.info("Guest deleted successfully with ID: {}", guestId);
    }
}

