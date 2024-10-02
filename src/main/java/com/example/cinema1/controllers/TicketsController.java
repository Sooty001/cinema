package com.example.cinema1.controllers;

import com.example.cinema1.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketsService ticketsService;

    @Autowired
    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @PostMapping("/adjust-prices")
    public ResponseEntity<String> adjustTicketPricesForAllSessions() {
        String message = ticketsService.adjustTicketPricesForAllSessions();
        return ResponseEntity.ok(message);
    }

    @PostMapping("/reserve/{ticketId}")
    public ResponseEntity<String> reserveTicket(@PathVariable int ticketId) {
        ticketsService.validateReservations();
        String message = ticketsService.reserveTicket(ticketId);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/purchase/{ticketId}/user/{userId}")
    public ResponseEntity<String> purchaseTicket(@PathVariable int ticketId, @PathVariable int userId) {
        ticketsService.validateReservations();
        String message = ticketsService.purchaseTicket(ticketId, userId);
        return ResponseEntity.ok(message);
    }
}