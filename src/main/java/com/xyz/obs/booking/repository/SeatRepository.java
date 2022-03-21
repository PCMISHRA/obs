package com.xyz.obs.booking.repository;

import com.xyz.obs.booking.entity.Seat;
import com.xyz.obs.booking.entity.SeatId;
import com.xyz.obs.booking.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, SeatId> ,TheaterSearchRepository {
     List<Seat> findAllByBookingId(Long bookingId);
     List<Seat> findAllBySeatIdInAndStatus(List<SeatId> seatCodes,  Status status);
}
