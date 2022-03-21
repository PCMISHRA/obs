package com.xyz.obs.booking.bean;

import com.xyz.obs.booking.entity.SeatId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BookingRequest {
   List<SeatId> seats;
   private PaymentDetails paymentDetails;
   //other bookking specific details

}
