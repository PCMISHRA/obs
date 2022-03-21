package com.xyz.obs.booking.entity;

import com.xyz.obs.booking.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@IdClass(HallId.class)
public class Hall {
    @Id
    @Column
    private Long hallId;
    @Id
    @Column
    private int theaterId;
    @Column
    private String hallName;

    @Column
    @OneToMany(fetch = FetchType.EAGER)
    private Set<MovieShow> movieShows;
    @Column
    private String description;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    @Version
    private int romVersion;





}
