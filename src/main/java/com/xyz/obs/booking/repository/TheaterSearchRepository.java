package com.xyz.obs.booking.repository;

import com.xyz.obs.booking.entity.TheaterView;
import com.xyz.obs.booking.bean.SearchCriteria;

import java.util.List;

public interface TheaterSearchRepository {
    List<TheaterView> search(List<SearchCriteria> criteria);
}
