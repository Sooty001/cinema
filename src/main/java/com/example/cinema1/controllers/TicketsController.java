package com.example.cinema1.controllers;

import com.example.cinema1.domain.Tickets;
import com.example.cinema1.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketsService ticketsService;

    @Autowired
    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @PostMapping("/adjust-prices/{sessionId}")
    public ResponseEntity<Void> adjustTicketPrices(@PathVariable int sessionId) {
        ticketsService.adjustTicketPrices(sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/reserve/{ticketId}")
    public ResponseEntity<Void> reserveTicket(@PathVariable int ticketId) {
        ticketsService.reserveTicket(ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/purchase/{ticketId}/user/{userId}")
    public ResponseEntity<Void> purchaseTicket(@PathVariable int ticketId, @PathVariable int userId) {
        ticketsService.purchaseTicket(ticketId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}