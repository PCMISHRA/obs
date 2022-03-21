package com.xyz.obs.booking.bean;

import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private Object value;
    private String operation;
}
