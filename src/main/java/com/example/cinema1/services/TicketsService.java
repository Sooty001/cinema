package com.example.cinema1.services;

import com.example.cinema1.domain.Tickets;
import com.example.cinema1.domain.Users;
import com.example.cinema1.relationships.Purchase;
import com.example.cinema1.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TicketsService {

    @PersistenceContext
    private EntityManager entityManager;
    private final TicketsRepository ticketsRepository;
    private final PurchaseRepository purchaseRepository;
    private final UsersRepository usersRepository;
    private final HallsRepository hallsRepository;

    @Autowired
    public TicketsService(TicketsRepository ticketsRepository, PurchaseRepository purchaseRepository, UsersRepository usersRepository, HallsRepository hallsRepository) {
        this.ticketsRepository = ticketsRepository;
        this.purchaseRepository = purchaseRepository;
        this.usersRepository = usersRepository;
        this.hallsRepository = hallsRepository;
    }

    @Transactional
    public void adjustTicketPrices(int sessionId) {
        Long soldTickets = ticketsRepository.countSoldTicketsBySessionId(sessionId);

        Integer totalSeats = hallsRepository.findHallSeatsBySessionId(sessionId);

        if (soldTickets > totalSeats / 2) {
            List<Tickets> availableTickets = ticketsRepository.findAvailableTicketsBySessionId(sessionId);

            for (Tickets ticket : availableTickets) {
                ticket.setPrice(ticket.getPrice() + 5);
                ticketsRepository.save(ticket);
            }
        }
    }


    @Transactional
    public void validateReservations() {
        List<Tickets> reservedTickets = ticketsRepository.findReservedTickets();
        LocalTime now = LocalTime.now();

        for (Tickets ticket : reservedTickets) {
            if (ChronoUnit.MINUTES.between(ticket.getChoice(), now) > 1) {
                ticket.setStatus("в наличии");
                ticketsRepository.save(ticket);
            }
        }
    }

    @Transactional
    public void reserveTicket(int ticketId) {
        validateReservations();
        Tickets ticket = ticketsRepository.findAvailableTicketById(ticketId);
        if (ticket != null) {
            ticket.setStatus("зарезервирован");
            ticket.setChoice(LocalTime.now());
            ticketsRepository.save(ticket);
        }
    }

    @Transactional
    public void purchaseTicket(int ticketId, int userId) {
        validateReservations();
        Tickets ticket = ticketsRepository.findById(ticketId).orElse(null);
        Users user = usersRepository.findById(userId).orElse(null);
        if (ticket != null && "зарезервирован".equals(ticket.getStatus())) {
            Purchase purchase = purchaseRepository.findByTicketsId(ticketId);
            purchase.setUsers(user);
            ticket.setStatus("продан");
            ticketsRepository.save(ticket);
        }
    }
}
