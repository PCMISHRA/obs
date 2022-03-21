package com.xyz.obs.booking.bean;

import com.xyz.obs.booking.SeatCategory;
import com.xyz.obs.booking.entity.MovieShow;
import com.xyz.obs.booking.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
public class SeatBean {

    private String seatCode;



    private MovieShow movieShow;

    private SeatCategory category;

    private BigDecimal price;


    private Long bookingId;

    private Status status;

    private int romVersion;

    /// audit cloumns




}
