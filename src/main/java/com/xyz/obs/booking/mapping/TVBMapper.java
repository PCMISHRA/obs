package com.xyz.obs.booking.mapping;

import com.xyz.obs.booking.bean.TheaterViewBean;
import com.xyz.obs.booking.entity.TheaterView;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TVBMapper {

    TheaterViewBean mapToBean(TheaterView theaterView);
    List<TheaterViewBean> mapToBean(List<TheaterView> theaterViews);
}
