package com.xyz.obs.booking.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SeatId implements Serializable {

    private Long movieShowId;
    @Column
    private String seatCode;



}
