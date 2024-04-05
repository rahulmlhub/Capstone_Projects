package com.metlife.hotel.controller;

import com.metlife.hotel.payload.HotelDTO;
import com.metlife.hotel.service.HotelService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    @Test
    void createHotel_ValidHotel_ReturnsCreatedHotel() {
        // Given
        HotelDTO inputHotelDTO = new HotelDTO();
        inputHotelDTO.setName("Hotel A");
        HotelDTO expectedHotelDTO = new HotelDTO();
        expectedHotelDTO.setName("Hotel A");
        when(hotelService.createHotel(inputHotelDTO)).thenReturn(expectedHotelDTO);

        // When
        ResponseEntity<HotelDTO> response = hotelController.createHotel(inputHotelDTO);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedHotelDTO, response.getBody());
    }

    @Test
    void updateHotel_ValidHotel_ReturnsUpdatedHotel() {
        // Given
        String hotelId = "1";
        HotelDTO inputHotelDTO = new HotelDTO();
        inputHotelDTO.setName("Updated Hotel A");
        HotelDTO expectedHotelDTO = new HotelDTO();
        expectedHotelDTO.setName("Updated Hotel A");
        when(hotelService.updateHotel(hotelId, inputHotelDTO)).thenReturn(expectedHotelDTO);

        // When
        ResponseEntity<HotelDTO> response = hotelController.updateHotel(hotelId, inputHotelDTO);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedHotelDTO, response.getBody());
    }

    @Test
    void getHotelById_ExistingHotelId_ReturnsHotelDTO() {
        // Given
        String hotelId = "1";
        HotelDTO expectedHotelDTO = new HotelDTO();
        expectedHotelDTO.setName("Hotel A");
        when(hotelService.getHotelById(hotelId)).thenReturn(expectedHotelDTO);

        // When
        ResponseEntity<HotelDTO> response = hotelController.getHotelById(hotelId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedHotelDTO, response.getBody());
    }

    @Test
    void getAllHotels_ReturnsListOfHotelDTOs() {
        // Given
        HotelDTO hotel1 = new HotelDTO();
        hotel1.setName("Hotel A");
        HotelDTO hotel2 = new HotelDTO();
        hotel2.setName("Hotel B");
        List<HotelDTO> expectedHotels = Arrays.asList(hotel1, hotel2);
        when(hotelService.getAllHotel()).thenReturn(expectedHotels);

        // When
        ResponseEntity<List<HotelDTO>> response = hotelController.getAllHotels();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedHotels, response.getBody());
    }

    @Test
    void deleteHotelById_ExistingHotelId_ReturnsNoContent() {
        // Given
        String hotelId = "1";

        // When
        ResponseEntity<Void> response = hotelController.deleteHotelById(hotelId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(hotelService, times(1)).deleteHotelById(hotelId);
    }

    @Test
    void getAllHotelsWithFacilities_ReturnsListOfHotelDTOsWithFacilities() {
        // Given
        HotelDTO hotel1 = new HotelDTO();
        hotel1.setName("Hotel A");
        HotelDTO hotel2 = new HotelDTO();
        hotel2.setName("Hotel B");
        List<HotelDTO> expectedHotels = Arrays.asList(hotel1, hotel2);
        when(hotelService.getAllHotelWithFacility()).thenReturn(expectedHotels);

        // When
        ResponseEntity<List<HotelDTO>> response = hotelController.getAllHotelsWithFacilities();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedHotels, response.getBody());
    }
}

