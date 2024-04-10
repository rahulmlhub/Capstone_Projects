package com.metlife.booking.proxy;

import com.metlife.booking.payload.GuestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GUEST-SERVICE")
public interface GuestProxy {

    @GetMapping("/api/guests/{guestId}")
    GuestDTO getGuestById(@PathVariable String guestId);
}
