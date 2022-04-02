package com.xyz.obs.booking.bean;

import com.xyz.obs.booking.entity.SeatId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BookingRequest {
   private List<SeatId> seats;

   //other bookking specific details

}
