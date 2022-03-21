package com.xyz.obs.booking.mapping;

import com.xyz.obs.booking.bean.SeatBean;
import com.xyz.obs.booking.entity.Seat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    SeatBean mapToBean(Seat theaterView);
    List<SeatBean> mapToBean(List<Seat> theaterViews);
}
