package com.metlife.hotel.service;

import com.metlife.hotel.payload.HotelDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Admin
 */
public interface HotelService {



    public List<HotelDTO> getAllHotelWithFacility();
    HotelDTO createHotel(HotelDTO hotelDTO);
    HotelDTO updateHotel(String hotelId, HotelDTO hotelDTO);
    HotelDTO getHotelById(String hotelId);
    List<HotelDTO> getAllHotel();
    void deleteHotelById(String hotelId);


}
