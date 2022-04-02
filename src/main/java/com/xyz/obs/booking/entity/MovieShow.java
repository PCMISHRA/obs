package com.xyz.obs.booking.entity;

import com.xyz.obs.booking.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class MovieShow {
    @Id
    @Column
    private Long movieShowId;


    @ManyToOne
    private Movie movie;


    @ManyToOne
    private Hall hall;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTme;

    @Column
    private String description;


    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Version
    @Column
    private int version;





}
