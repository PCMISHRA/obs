package com.xyz.obs.booking.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDateTime;

@Entity
@IdClass(TheaterViewId.class)
@Getter
public class TheaterView {
    @Id
    @Column
    private Long hallId;
    @Id
    @Column
    private int theaterId;
    @Column
    private String hallName;
    @Column
    private String theaterName;
    @Id
    @Column
    private Long movieShowId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String city;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTme;




}
