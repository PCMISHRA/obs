package com.xyz.obs.booking.service;

import com.xyz.obs.booking.bean.SearchCriteria;
import com.xyz.obs.booking.bean.TheaterViewBean;
import com.xyz.obs.booking.mapping.TVBMapper;
import com.xyz.obs.booking.repository.TheaterViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    private final TheaterViewRepository theaterViewRepository;
    private final TVBMapper tvbMapper;

    public TheaterService(TheaterViewRepository theaterViewRepository, TVBMapper tvbMapper) {
        this.theaterViewRepository = theaterViewRepository;
        this.tvbMapper = tvbMapper;
    }

    public List<TheaterViewBean> search(List<SearchCriteria> criterias){
        return  tvbMapper.mapToBean( theaterViewRepository.search(criterias));


    }
}
