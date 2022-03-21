package com.xyz.obs.booking.repository;

import com.xyz.obs.booking.entity.TheaterView;
import com.xyz.obs.booking.entity.TheaterViewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterViewRepository extends JpaRepository<TheaterView, TheaterViewId> ,TheaterSearchRepository {
}
