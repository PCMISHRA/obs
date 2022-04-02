package com.xyz.obs.booking.service;

import com.xyz.obs.booking.BookingApplication;
import com.xyz.obs.booking.bean.*;
import com.xyz.obs.booking.entity.Booking;
import com.xyz.obs.booking.entity.MovieShow;
import com.xyz.obs.booking.entity.Seat;
import com.xyz.obs.booking.enums.Status;
import com.xyz.obs.booking.exception.InvalidRequestException;
import com.xyz.obs.booking.mapping.SeatMapper;
import com.xyz.obs.booking.repository.MovieShowRepository;
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
    private final SeatRepository seatRepository;
    private final PriceCalculatorService priceCalculatorService;
    private final MovieShowRepository movieShowRepository;

    private RabbitTemplate rabbitTemplate;

    public BookingService(SeatMapper seatMapper, SeatRepository seatRepository,
                          PriceCalculatorService priceCalculatorService, MovieShowRepository movieShowRepository, RabbitTemplate rabbitTemplate) {
        this.seatMapper = seatMapper;

        this.seatRepository = seatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.movieShowRepository = movieShowRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public BookingResult bookTickets(BookingRequest bookingRequest) {
        List<Seat> seats = seatRepository.findAllBySeatIdInAndStatus(bookingRequest.getSeats(), Status.NOT_BOOKED);
        if (bookingRequest.getSeats().size() != seats.size()) {
            // some seat all ready ouccpied;
            throw new InvalidRequestException("Some of seats are already booked");
        }
      //take  Lock for seat booking
       Long movieShowId= bookingRequest.getSeats().get(0).getMovieShowId();
        MovieShow seatLock = movieShowRepository.findByMovieShowId(movieShowId).orElseThrow();

        Booking booking = new Booking();
        booking.setSeats(seats);
        booking.setBookingTime(LocalDateTime.now());
       // grpc Call; /// call service to get price
        booking.setAmount(priceCalculatorService.getPrice(bookingRequest.getSeats(), new UserDeatils()));


        final BookingResult bookingResult = new BookingResult();
        bookingResult.setBookingId(booking.getBookingId());
        bookingResult.setSeats(new ArrayList<>());
        //update cache(  to keep track for a expired reqcord)
        Long bookingId =  updateCache(seats);
        // block seats
        seats.forEach(seat -> blockSeats(seat, bookingResult));


        bookingResult.setBookingId(bookingId);


        //
        seatRepository.saveAll(seats);
        return bookingResult;

    }

    private Long updateCache(List<Seat> seats) {
        //TOD Impl

        return null;
    }

    @Transactional
    public ConfirmationResponse confirmTickets(ConfirmationRequest confirmationRequest) {
        ConfirmationResponse confirmationResponse = new ConfirmationResponse();
       confirmBooking(confirmationRequest,confirmationResponse);

       notifyCustomer(true);

       return confirmationResponse;
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


    private void confirmBooking(ConfirmationRequest confirmationRequest,
                                          ConfirmationResponse confirmationResponse) {
        //todo may payment
        confirmationResponse.setTransactionDetails("tran1");
        //Todo read from cache  if payment successful on basis of booking id
        List <Seat> seats = new ArrayList<>();
        seats.forEach(e->e.setStatus(Status.BOOKED));

        confirmationResponse.setSeats(seatMapper.mapToBean(seatRepository.saveAll(seats)));
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
