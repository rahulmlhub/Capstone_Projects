package com.metlife.guests.service;

/**
 * @author Admin
 */
import com.metlife.guests.payload.GuestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GuestService {
    GuestDTO createGuest(GuestDTO guestDTO);
    GuestDTO updateGuest(String guestId, GuestDTO guestDTO);
    GuestDTO getGuestById(String guestId);
    List<GuestDTO> getAllGuests();
    void deleteGuestById(String guestId);
}

