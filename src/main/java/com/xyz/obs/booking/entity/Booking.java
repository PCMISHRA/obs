package com.xyz.obs.booking.entity;

import com.xyz.obs.booking.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Booking {
    @Id
    @Column
    private Long bookingId;

    @Column
    @OneToMany
    private List<Seat> seats;

    @Column
    private LocalDateTime bookingTime;
    @Column
    private String transactionId;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String description;

    @Column
    private BigDecimal amount;

    @Column
    @Version
    private int romVersion;


}
