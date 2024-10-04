package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Tickets;
import com.example.cinema1.repositories.TicketsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketsRepositoryImpl implements TicketsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer countSoldTicketsBySessionId(int sessionId) {
        String query = "SELECT COUNT(t) FROM Tickets t JOIN Purchase p ON t.id = p.tickets.id WHERE p.sessions.id = :sessionId AND t.status = 'продан'";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("sessionId", sessionId)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Tickets> findAvailableTicketsBySessionId(int sessionId) {
        String query = "SELECT t FROM Tickets t JOIN Purchase p ON t.id = p.tickets.id WHERE p.sessions.id = :sessionId AND t.status = 'в наличии'";
        return entityManager.createQuery(query, Tickets.class)
                .setParameter("sessionId", sessionId)
                .getResultList();
    }

    @Override
    public List<Tickets> findReservedTickets() {
        String query = "SELECT t FROM Tickets t WHERE t.status = 'зарезервирован'";
        return entityManager.createQuery(query, Tickets.class)
                .getResultList();
    }

    @Override
    public Tickets findAvailableTicketById(int ticketId) {
        String query = "SELECT t FROM Tickets t WHERE t.id = :ticketId AND t.status = 'в наличии'";
        List<Tickets> tickets = entityManager.createQuery(query, Tickets.class)
                .setParameter("ticketId", ticketId)
                .getResultList();
        return tickets.isEmpty() ? null : tickets.get(0);
    }

    @Override
    public Optional<Tickets> findById(Integer ticketId) {
        return Optional.ofNullable(entityManager.find(Tickets.class, ticketId));
    }
}
