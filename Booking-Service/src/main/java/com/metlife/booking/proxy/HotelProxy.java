package com.metlife.booking.proxy;

import com.metlife.booking.payload.HotelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelProxy {

    @GetMapping("/api/hotels/{hotelId}")
    HotelDTO getHotelById(@PathVariable String hotelId);
}

