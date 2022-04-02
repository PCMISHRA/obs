package com.xyz.obs.booking.bean;

import lombok.Data;

import java.util.List;

@Data
public class ConfirmationResponse {
    private String transactionDetails;
    private List<SeatBean> seats;
}
