package com.xyz.obs.booking.entity;

import com.xyz.obs.booking.SeatCategory;
import com.xyz.obs.booking.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data

public class Seat {
   @EmbeddedId
   private SeatId seatId;

    @MapsId("movieShowId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "movieShowId",referencedColumnName = "movieShowId")
    private MovieShow movieShow;
    @Column
    private SeatCategory category;
    @Column
    private BigDecimal price;

    @Column
    private Long bookingId;
    @Column
    private Status status;
    @Column
    @Version
    private int romVersion;

    /// audit cloumns




}
