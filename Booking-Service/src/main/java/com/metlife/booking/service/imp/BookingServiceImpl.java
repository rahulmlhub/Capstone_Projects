package com.metlife.booking.service.imp;

import com.metlife.booking.entity.Booking;
import com.metlife.booking.exception.ResourceNotFoundException;
import com.metlife.booking.payload.BookingDTO;
import com.metlife.booking.repository.BookingRepository;
import com.metlife.booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        logger.info("Creating a new booking");
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDTO, booking);
        Booking savedBooking = bookingRepository.save(booking);
        BookingDTO savedBookingDTO = new BookingDTO();
        BeanUtils.copyProperties(savedBooking, savedBookingDTO);
        logger.info("Booking created successfully with ID: {}", savedBookingDTO.getBookingId());
        return savedBookingDTO;
    }

    @Override
    public BookingDTO updateBooking(String bookingId, BookingDTO bookingDTO) {
        logger.info("Updating booking with ID: {}", bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking "," ID: " , bookingId));
        BeanUtils.copyProperties(bookingDTO, booking);
        Booking updatedBooking = bookingRepository.save(booking);
        BookingDTO updatedBookingDTO = new BookingDTO();
        BeanUtils.copyProperties(updatedBooking, updatedBookingDTO);
        logger.info("Booking updated successfully with ID: {}", bookingId);
        return updatedBookingDTO;
    }

    @Override
    public BookingDTO getBookingById(String bookingId) {
        logger.info("Fetching booking with ID: {}", bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking "," ID: " , bookingId));
        BookingDTO bookingDTO = new BookingDTO();
        BeanUtils.copyProperties(booking, bookingDTO);
        logger.info("Booking fetched successfully with ID: {}", bookingId);
        return bookingDTO;
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        logger.info("Fetching all bookings");
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDTO> bookingDTOs = bookings.stream()
                .map(booking -> {
                    BookingDTO bookingDTO = new BookingDTO();
                    BeanUtils.copyProperties(booking, bookingDTO);
                    return bookingDTO;
                })
                .collect(Collectors.toList());
        logger.info("Fetched {} bookings", bookingDTOs.size());
        return bookingDTOs;
    }

    @Override
    public void deleteBookingById(String bookingId) {
        logger.info("Deleting booking with ID: {}", bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking "," ID: " , bookingId));
        bookingRepository.delete(booking);
        logger.info("Booking deleted successfully with ID: {}", bookingId);
    }
}

