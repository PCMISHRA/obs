package com.xyz.obs.booking.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {
    private String city;
    private String area;
    private String country;
    private String zip;
    private String addressLine1;
    private String addressLine2;
}
