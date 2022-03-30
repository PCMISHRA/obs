package com.xyz.obs.booking.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private  String message;
    private int code;
    private LocalDateTime time;
}
