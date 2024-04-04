package com.metlife.hotel.service.imp;

import com.metlife.hotel.entity.Hotel;
import com.metlife.hotel.exception.ResourceNotFoundException;
import com.metlife.hotel.payload.HotelDTO;
import com.metlife.hotel.repository.HotelRepository;
import com.metlife.hotel.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Admin
 */
@Service
public class HotelServiceImpl implements HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

   final private HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        logger.info("Creating a new hotel");
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelDTO, hotel);
        Hotel savedHotel = hotelRepository.save(hotel);
        HotelDTO savedHotelDTO = new HotelDTO();
        BeanUtils.copyProperties(savedHotel, savedHotelDTO);
        logger.info("Hotel created successfully with ID: {}", savedHotelDTO.getHotelId());
        return savedHotelDTO;
    }

    @Override
    public HotelDTO updateHotel(String hotelId, HotelDTO hotelDTO) {
        logger.info("Updating hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not Found", "hotelId", hotelId));
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setPhoneNumber(hotelDTO.getPhoneNumber());
        Hotel updatedHotel = hotelRepository.save(hotel);

        HotelDTO updatedHotelDTO = new HotelDTO();
        BeanUtils.copyProperties(updatedHotel, updatedHotelDTO);

        logger.info("Hotel updated successfully with ID: {}", hotelId);
        return updatedHotelDTO;
    }

    @Override
    public HotelDTO getHotelById(String hotelId) {
        logger.info("Fetching hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not Found", "hotelId", hotelId));
        HotelDTO hotelDTO = new HotelDTO();
        BeanUtils.copyProperties(hotel, hotelDTO);
        logger.info("Hotel fetched successfully with ID: {}", hotelId);
        return hotelDTO;
    }

    @Override
    public List<HotelDTO> getAllHotel() {
        logger.info("Fetching all hotels");
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelDTO> hotelDTOs = hotels.stream()
                .map(hotel -> {
                    HotelDTO hotelDTO = new HotelDTO();
                    BeanUtils.copyProperties(hotel, hotelDTO);
                    return hotelDTO;
                })
                .collect(Collectors.toList());
        logger.info("Fetched {} hotels", hotelDTOs.size());
        return hotelDTOs;
    }

    @Override
    public void deleteHotelById(String hotelId) {
        logger.info("Deleting hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not Found", "hotelId", hotelId));
        hotelRepository.delete(hotel);
        logger.info("Hotel deleted successfully with ID: {}", hotelId);
    }
}
