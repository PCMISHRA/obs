package com.xyz.obs.booking.service;

import com.xyz.obs.booking.bean.UserDeatils;
import com.xyz.obs.booking.entity.SeatId;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PriceCalculatorService {
   public BigDecimal getPrice(List<SeatId> seatIds, UserDeatils userDeatils){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        //TODO impl
        return BigDecimal.ONE;
    }
}
