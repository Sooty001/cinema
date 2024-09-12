package com.example.cinema1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UserNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTicketNotFound(TicketNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TicketUnavailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTicketUnavailable(TicketUnavailableException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TicketAlreadyReservedOrSoldException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleTicketAlreadyReservedOrSold(TicketAlreadyReservedOrSoldException e) {
        return e.getMessage();
    }
}