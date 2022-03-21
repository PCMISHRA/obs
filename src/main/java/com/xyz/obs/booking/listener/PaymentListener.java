package com.xyz.obs.booking.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Map;
@Log4j2
@Component
public class PaymentListener {


    public PaymentListener() {

    }

    public void receiveMessage(Map<String, String> message) {

        Long booking = Long.valueOf(message.get("id"));

        log.info("Payment recivved");
    }
}
