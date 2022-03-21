package com.xyz.obs.booking.controller;

import com.xyz.obs.booking.bean.SearchCriteria;
import com.xyz.obs.booking.bean.TheaterViewBean;
import com.xyz.obs.booking.service.TheaterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("theaters")
public class TheaterController {
    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping
    public List<TheaterViewBean> searchByCriterias(List<SearchCriteria> criteria){
      return   theaterService.search(criteria);
    }
}
