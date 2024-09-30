package com.example.cinema1.services;

import com.example.cinema1.exceptions.TicketAlreadyReservedOrSoldException;
import com.example.cinema1.exceptions.TicketNotFoundException;
import com.example.cinema1.exceptions.TicketUnavailableException;
import com.example.cinema1.exceptions.UserNotFoundException;

public interface TicketsService {
    String adjustTicketPricesForAllSessions();
    void validateReservations();
    String reserveTicket(int ticketId) throws TicketNotFoundException, TicketAlreadyReservedOrSoldException;
    String purchaseTicket(int ticketId, int userId) throws TicketNotFoundException, TicketUnavailableException, UserNotFoundException;
}
