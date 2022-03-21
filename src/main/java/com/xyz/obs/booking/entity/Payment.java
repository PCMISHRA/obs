package com.xyz.obs.booking.entity;

import com.xyz.obs.booking.enums.PaymentType;
import com.xyz.obs.booking.enums.Status;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Payment {
    @Id
    @Column
    private Long paymentId;
    @Column
    private Long bookingId;
    @Column
    private String transactionId;

    @Column
    private LocalDateTime transactionDate;

    @Column
    private PaymentType paymentType;

    @Column
    private Status status;

    @Column
    private String description;

    @Column
    private BigDecimal amount;

    @Column
    @Version
    private int romVersion;


}
