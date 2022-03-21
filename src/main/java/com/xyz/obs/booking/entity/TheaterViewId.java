package com.xyz.obs.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class TheaterViewId implements Serializable {

    private Long hallId;

    private int theaterId;

    private Long movieShowId;





}
