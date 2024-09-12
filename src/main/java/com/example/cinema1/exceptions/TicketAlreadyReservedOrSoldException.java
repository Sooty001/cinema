package com.example.cinema1.exceptions;

public class TicketAlreadyReservedOrSoldException extends RuntimeException {
    public TicketAlreadyReservedOrSoldException(int ticketId) {
        super("Ticket with ID " + ticketId + " is already reserved or sold.");
    }
}