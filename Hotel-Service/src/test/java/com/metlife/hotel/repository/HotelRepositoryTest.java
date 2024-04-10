package com.metlife.hotel.repository;
import com.metlife.hotel.entity.Hotel;
import com.metlife.hotel.entity.Facility;
import com.metlife.hotel.exception.ResourceNotFoundException;
import com.metlife.hotel.payload.HotelDTO;
import com.metlife.hotel.repository.HotelRepository;
import com.metlife.hotel.service.imp.HotelServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class HotelRepositoryTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void saveHotel_ValidHotel_ReturnsSavedHotel() {
        // Given
        HotelDTO hotelDTO = new HotelDTO("1", "Hotel A", "Address A", "1234567890");
        Hotel hotelEntity = new Hotel();
        BeanUtils.copyProperties(hotelDTO, hotelEntity);
        when(hotelRepository.save(hotelEntity)).thenReturn(hotelEntity);

        // When
//        HotelDTO savedHotelDTO = hotelService.createHotel(hotelDTO);

        // Then
//        assertNotNull(savedHotelDTO);
//        assertEquals(hotelDTO.getHotelId(), savedHotelDTO.getHotelId());
//        assertEquals(hotelDTO.getName(), savedHotelDTO.getName());
//        assertEquals(hotelDTO.getAddress(), savedHotelDTO.getAddress());
//        assertEquals(hotelDTO.getPhoneNumber(), savedHotelDTO.getPhoneNumber());
    }

    @Test
    void updateHotel_ExistingHotel_ReturnsUpdatedHotel() {
        // Given
        String hotelId = "1";
        HotelDTO existingHotelDTO = new HotelDTO(hotelId, "Hotel A", "Address A", "1234567890");
        HotelDTO updatedHotelDTO = new HotelDTO(hotelId, "Updated Hotel", "Updated Address", "0987654321");

        Hotel existingHotelEntity = new Hotel();
        BeanUtils.copyProperties(existingHotelDTO, existingHotelEntity);

        Hotel updatedHotelEntity = new Hotel();
        BeanUtils.copyProperties(updatedHotelDTO, updatedHotelEntity);

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(existingHotelEntity));
        when(hotelRepository.save(existingHotelEntity)).thenReturn(updatedHotelEntity);

        // When
        HotelDTO returnedUpdatedHotelDTO = hotelService.updateHotel(hotelId, updatedHotelDTO);

        // Then
        assertNotNull(returnedUpdatedHotelDTO);
        assertEquals(updatedHotelDTO.getHotelId(), returnedUpdatedHotelDTO.getHotelId());
        assertEquals(updatedHotelDTO.getName(), returnedUpdatedHotelDTO.getName());
        assertEquals(updatedHotelDTO.getAddress(), returnedUpdatedHotelDTO.getAddress());
        assertEquals(updatedHotelDTO.getPhoneNumber(), returnedUpdatedHotelDTO.getPhoneNumber());
    }
    @Test
    void getHotelById_ExistingHotelId_ReturnsHotelDTO() {
        // Given
        String hotelId = "1";
        Hotel hotel = new Hotel(hotelId, "Hotel A", "Address A", "1234567890");

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        // When
        HotelDTO hotelDTO = hotelService.getHotelById(hotelId);

        // Then
        assertNotNull(hotelDTO);
        assertEquals(hotel.getHotelId(), hotelDTO.getHotelId());
        assertEquals(hotel.getName(), hotelDTO.getName());
        assertEquals(hotel.getAddress(), hotelDTO.getAddress());
        assertEquals(hotel.getPhoneNumber(), hotelDTO.getPhoneNumber());
    }

    @Test
    void getAllHotel_ReturnsListOfHotelDTOs() {
        // Given
        List<Hotel> hotels = Arrays.asList(
                new Hotel("1", "Hotel A", "Address A", "1234567890"),
                new Hotel("2", "Hotel B", "Address B", "0987654321")
        );

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
        List<Hotel> hotels = Arrays.asList(
                new Hotel("1", "Hotel A", "Address A", "1234567890"),
                new Hotel("2", "Hotel B", "Address B", "0987654321")
        );

        hotels.get(0).setFacilities(new HashSet<>(Arrays.asList(new Facility("1", "Facility A"), new Facility("2", "Facility B"))));
        hotels.get(1).setFacilities(new HashSet<>(Arrays.asList(new Facility("3", "Facility C"), new Facility("4", "Facility D"))));

        when(hotelRepository.findAllWithFacilities()).thenReturn(hotels);

        // When
        List<HotelDTO> hotelDTOs = hotelService.getAllHotelWithFacility();

        // Then
        assertNotNull(hotelDTOs);
        assertEquals(2, hotelDTOs.size());
        assertEquals(2, hotelDTOs.get(0).getFacilities().size());
        assertEquals(2, hotelDTOs.get(1).getFacilities().size());
    }

    @Test
    void deleteHotelById_ExistingHotelId_DeletesHotel() {
        // Given
        String hotelId = "1";
        Hotel existingHotel = new Hotel(hotelId, "Hotel A", "Address A", "1234567890");

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
