package com.xyz.obs.booking.service;

import com.xyz.obs.booking.BookingApplication;
import com.xyz.obs.booking.bean.BookingRequest;
import com.xyz.obs.booking.bean.BookingResult;
import com.xyz.obs.booking.bean.ConfirmationRequest;
import com.xyz.obs.booking.entity.Booking;
import com.xyz.obs.booking.entity.Seat;
import com.xyz.obs.booking.enums.Status;
import com.xyz.obs.booking.exception.InvalidRequestException;
import com.xyz.obs.booking.mapping.SeatMapper;
import com.xyz.obs.booking.repository.SeatRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class BookingService {
    private final SeatMapper seatMapper;
    private final SeatRepository repository;
    private RabbitTemplate rabbitTemplate;

    public BookingService(SeatMapper seatMapper, SeatRepository repository, RabbitTemplate rabbitTemplate) {
        this.seatMapper = seatMapper;

        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public BookingResult bookTickets(BookingRequest bookingRequest) {
        List<Seat> seats = repository.findAllBySeatIdInAndStatus(bookingRequest.getSeats(), Status.NOT_BOOKED);
        if (bookingRequest.getSeats().size() != seats.size()) {
            // some seat all ready ouccpied;
            throw new InvalidRequestException("Some of seats are already booked");
        }
        // TODO amout check

        Booking booking = new Booking();
        booking.setSeats(seats);
        booking.setBookingTime(LocalDateTime.now());
        booking.setAmount(bookingRequest.getPaymentDetails().getAmount());


        final BookingResult bookingResult = new BookingResult();
        bookingResult.setBookingId(booking.getBookingId());
        bookingResult.setSeats(new ArrayList<>());
        // block seats
        seats.forEach(seat -> blockSeats(seat, bookingResult));

       notifyPayment(bookingResult);

        repository.saveAll(seats);

        //
        return bookingResult;

    }
    @Transactional
    public void confirmTickets(ConfirmationRequest confirmationRequest) {
        
       List<Seat> seats= confirmBooking(confirmationRequest);
       confirmSeats(seats);
       notifyCustomer(true);
       }
    @Transactional
    public void releaseTickets(ConfirmationRequest confirmationRequest) {
        List<Seat> seats= cancelBooking(confirmationRequest);
        releaseSeats(seats);
        notifyCustomer(false);
    }

    private void releaseSeats(List<Seat> seats) {
    }

    private List<Seat> cancelBooking(ConfirmationRequest confirmationRequest) {
        //todoimpl
        return new ArrayList<>();
    }

    private void notifyCustomer(boolean confirmed) {
    }

    private void confirmSeats(List<Seat> seats) {
    }

    private List<Seat> confirmBooking(ConfirmationRequest confirmationRequest) {
        //TOD impl
        return new ArrayList<>();
    }

    private void blockSeats(Seat seat, BookingResult bookingResult) {

        seat.setStatus(Status.BLOCKED);
        seat.setBookingId(bookingResult.getBookingId());
        bookingResult.getSeats().add(seatMapper.mapToBean(seat));
    }

    public void notifyPayment(BookingResult bookingResult) {
        Map<String, String> actionmap = new HashMap<>();
        actionmap.put("id", ""+bookingResult.getBookingId());
        log.info("Sending the payment request for {}",bookingResult.getBookingId());
        rabbitTemplate.convertAndSend(BookingApplication.PAYMENT_MESSAGE_QUEUE, actionmap);
    }

}
