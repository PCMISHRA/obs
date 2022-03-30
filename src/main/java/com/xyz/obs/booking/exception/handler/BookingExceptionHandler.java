package com.xyz.obs.booking.exception.handler;

import com.xyz.obs.booking.bean.ErrorMessage;
import com.xyz.obs.booking.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException invalidRequestException, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(invalidRequestException.getMessage());
        errorMessage.setCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setTime(LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
