package com.xyz.obs.booking.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Slot {
    SLOT_1("Morning","10:00","1:00");
    private final String description;
    private final String startTime;
    private final String endTime;
}
