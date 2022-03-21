package com.xyz.obs.booking.controller;

import com.xyz.obs.booking.bean.BookingRequest;
import com.xyz.obs.booking.bean.BookingResult;
import com.xyz.obs.booking.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public BookingResult bookTicket(BookingRequest bookingRequest) {
      return   bookingService.bookTickets(bookingRequest);
    }
}
