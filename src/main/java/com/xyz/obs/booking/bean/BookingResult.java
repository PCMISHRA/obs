package com.xyz.obs.booking.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class BookingResult {
    private BigDecimal amount;
    private List<SeatBean> seats;
    private Long bookingId;

    // other details

}
