package com.example.cinema1.repositories;

import com.example.cinema1.domain.Tickets;
import java.util.List;
import java.util.Optional;

public interface TicketsRepository {
    Integer countSoldTicketsBySessionId(int sessionId);
    List<Tickets> findAvailableTicketsBySessionId(int sessionId);
    List<Tickets> findReservedTickets();
    Tickets findAvailableTicketById(int ticketId);
    Optional<Tickets> findById(Integer ticketId);
}