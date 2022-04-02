package com.xyz.obs.booking.repository;

import com.xyz.obs.booking.entity.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow, Long>  {
     @Lock(value = LockModeType.PESSIMISTIC_FORCE_INCREMENT)
     Optional<MovieShow> findByMovieShowId (Long movieShowId);

}
