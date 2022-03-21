package com.xyz.obs.booking.entity;

import com.xyz.obs.booking.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Theater {

    @Id
    @Column
    private int theaterId;
    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Hall> halls;

    @Column
    private String name;
    @Column
    private Long partnerId;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    @Version
    private int romVersion;





}
