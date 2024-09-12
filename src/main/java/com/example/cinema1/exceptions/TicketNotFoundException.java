package com.example.cinema1.exceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(int ticketId) {
        super("Ticket with id " + ticketId + " not found");
    }
}