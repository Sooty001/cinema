package com.example.cinema1.services.impl;

import com.example.cinema1.domain.Tickets;
import com.example.cinema1.domain.Users;
import com.example.cinema1.domain.Purchase;
import com.example.cinema1.exceptions.*;
import com.example.cinema1.repositories.*;
import com.example.cinema1.services.TicketsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TicketsServiceImpl implements TicketsService {

    private final TicketsRepository ticketsRepository;
    private final PurchaseRepository purchaseRepository;
    private final UsersRepository usersRepository;
    private final HallsRepository hallsRepository;
    private final CUDRepository CUDRepository;

    @Autowired
    public TicketsServiceImpl(TicketsRepository ticketsRepository, PurchaseRepository purchaseRepository, UsersRepository usersRepository, HallsRepository hallsRepository, CUDRepository CUDRepository) {
        this.ticketsRepository = ticketsRepository;
        this.purchaseRepository = purchaseRepository;
        this.usersRepository = usersRepository;
        this.hallsRepository = hallsRepository;
        this.CUDRepository = CUDRepository;

        this.CUDRepository.setCreate(false);
        this.CUDRepository.setUpdate(true);
        this.CUDRepository.setDelete(false);
    }

    @Override
    @Transactional
    public String adjustTicketPricesForAllSessions() {
        List<Integer> sessionIds = purchaseRepository.findAllSessionIds();

        for (int sessionId : sessionIds) {
            Integer soldTickets = ticketsRepository.countSoldTicketsBySessionId(sessionId);
            Integer totalSeats = hallsRepository.findHallSeatsBySessionId(sessionId);

            if (soldTickets > totalSeats / 2 && !totalSeats.equals(soldTickets)) {
                List<Tickets> availableTickets = ticketsRepository.findAvailableTicketsBySessionId(sessionId);

                for (Tickets ticket : availableTickets) {
                    ticket.setPrice(ticket.getPrice() + 5);
                    CUDRepository.updateEntity(ticket);
                }
                return "Успешно.";
            }
        }
        return "Таких сеансов нет.";
    }

    @Override
    public void validateReservations() {
        List<Tickets> reservedTickets = ticketsRepository.findReservedTickets();
        LocalTime now = LocalTime.now();

        for (Tickets ticket : reservedTickets) {
            if (ChronoUnit.MINUTES.between(ticket.getChoice(), now) > 15) {
                ticket.setStatus("в наличии");
                CUDRepository.updateEntity(ticket);
            }
        }
    }

    @Override
    public String reserveTicket(int ticketId) {
        if (ticketsRepository.findById(ticketId).isEmpty()) {
            throw new TicketNotFoundException(ticketId);
        }
        Tickets ticket = ticketsRepository.findAvailableTicketById(ticketId);
        if (ticket != null) {
            ticket.setStatus("зарезервирован");
            ticket.setChoice(LocalTime.now());
            CUDRepository.updateEntity(ticket);
            return "Билет успешно зарезервирован.";
        }
        throw new TicketAlreadyReservedOrSoldException(ticketId);
    }

    @Override
    @Transactional
    public String purchaseTicket(int ticketId, int userId) {
        Tickets ticket = ticketsRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException(ticketId));
        Users user = usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (!"зарезервирован".equals(ticket.getStatus())) {
            throw new TicketUnavailableException(ticketId);
        }

        Purchase purchase = purchaseRepository.findByTicketsId(ticketId);
        purchase.setUsers(user);
        ticket.setStatus("продан");

        CUDRepository.updateEntity(purchase);
        CUDRepository.updateEntity(ticket);

        return "Билет успешно куплен.";
    }
}
