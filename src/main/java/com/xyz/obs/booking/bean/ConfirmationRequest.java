package com.xyz.obs.booking.bean;

import lombok.Data;

@Data
public class ConfirmationRequest {
    private Long bookingId;
    private PaymentDetails paymentDetails;
}
