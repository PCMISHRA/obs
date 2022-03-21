package com.xyz.obs.booking.entity;

import com.xyz.obs.booking.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @Column
    private Long movieId;


    @Column
    private String movieJson;

    @Column
    private String name;

    @Column
    @OneToMany
    private List<MovieShow> movieShows ;

    @Column
    private String description;


    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    @Version
    private int romVersion;





}
