package com.example.cinema1.exceptions;

public class TicketUnavailableException extends RuntimeException {
    public TicketUnavailableException(int ticketId) {
        super("Ticket with id " + ticketId + " is unavailable or reservation time has expired.");
    }
}