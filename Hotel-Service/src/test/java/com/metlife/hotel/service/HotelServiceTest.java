package com.metlife.hotel.service;

import com.metlife.hotel.entity.Facility;
import com.metlife.hotel.entity.Hotel;
import com.metlife.hotel.exception.ResourceNotFoundException;
import com.metlife.hotel.payload.HotelDTO;
import com.metlife.hotel.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createHotel_ValidHotel_ReturnsHotelDTO() {
        // Given
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setName("Hotel A");

        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelDTO, hotel);

        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        // When
//        HotelDTO createdHotelDTO = hotelService.createHotel(hotelDTO);

        // Then
//        assertNotNull(createdHotelDTO);
//        assertEquals(hotelDTO.getName(), createdHotelDTO.getName());
    }

    @Test
    void updateHotel_ValidHotel_ReturnsUpdatedHotelDTO() {
        // Given
        String hotelId = "1";
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotelId(hotelId);
        hotelDTO.setName("Hotel A");

        Hotel existingHotel = new Hotel();
        existingHotel.setHotelId(hotelId);
        existingHotel.setName("Old Hotel");

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(existingHotel));
        when(hotelRepository.save(any(Hotel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        HotelDTO updatedHotelDTO = hotelService.updateHotel(hotelId, hotelDTO);

        // Then
        assertNotNull(updatedHotelDTO);
        assertEquals(hotelDTO.getName(), updatedHotelDTO.getName());
    }

    @Test
    void getHotelById_ExistingHotelId_ReturnsHotelDTO() {
        // Given
        String hotelId = "1";
        Hotel hotel = new Hotel();
        hotel.setHotelId(hotelId);
        hotel.setName("Hotel A");

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        // When
        HotelDTO foundHotelDTO = hotelService.getHotelById(hotelId);

        // Then
        assertNotNull(foundHotelDTO);
        assertEquals(hotel.getName(), foundHotelDTO.getName());
    }

    @Test
    void getHotelById_NonExistingHotelId_ThrowsResourceNotFoundException() {
        // Given
        String hotelId = "1";
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(ResourceNotFoundException.class, () -> hotelService.getHotelById(hotelId));
    }

    @Test
    void getAllHotel_ReturnsListOfHotelDTOs() {
        // Given
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("1", "Hotel A", "Address 1", "1234567890"));
//        hotels.add(new Hotel("2", "Hotel B"));

        when(hotelRepository.findAll()).thenReturn(hotels);

        // When
        List<HotelDTO> hotelDTOs = hotelService.getAllHotel();

        // Then
        assertNotNull(hotelDTOs);
        assertEquals(2, hotelDTOs.size());
    }

    @Test
    void getAllHotelWithFacility_ReturnsListOfHotelDTOsWithFacilities() {
        // Given
        List<Hotel> hotels = new ArrayList<>();
        Hotel hotel1 = new Hotel("1", "Hotel A", "Address 1", "1234567890");
        hotel1.setFacilities(new HashSet<>(Arrays.asList(new Facility("1", "Facility A"), new Facility("2", "Facility B"))));
        Hotel hotel2 = new Hotel("1", "Hotel A", "Address 1", "1234567890");
        hotel2.setFacilities(new HashSet<>(Collections.singletonList(new Facility("3", "Facility C"))));
        hotels.add(hotel1);
        hotels.add(hotel2);

        when(hotelRepository.findAllWithFacilities()).thenReturn(hotels);

        // When
        List<HotelDTO> hotelDTOs = hotelService.getAllHotelWithFacility();

        // Then
        assertNotNull(hotelDTOs);
        assertEquals(2, hotelDTOs.size());
        assertEquals(2, hotelDTOs.get(0).getFacilities().size());
        assertEquals(1, hotelDTOs.get(1).getFacilities().size());
    }

    @Test
    void deleteHotelById_ExistingHotelId_DeletesHotel() {
        // Given
        String hotelId = "1";
        Hotel existingHotel = new Hotel();
        existingHotel.setHotelId(hotelId);

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(existingHotel));

        // When
        hotelService.deleteHotelById(hotelId);

        // Then
        verify(hotelRepository, times(1)).delete(existingHotel);
    }

    @Test
    void deleteHotelById_NonExistingHotelId_ThrowsResourceNotFoundException() {
        // Given
        String hotelId = "1";
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(ResourceNotFoundException.class, () -> hotelService.deleteHotelById(hotelId));
    }
}


