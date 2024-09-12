package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Tickets;
import com.example.cinema1.repositories.TicketsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketsRepositoryImpl implements TicketsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer countSoldTicketsBySessionId(int sessionId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM Tickets t WHERE t.purchase.sessions.id = :sessionId AND t.status = 'продан'",
                Long.class);
        query.setParameter("sessionId", sessionId);
        return query.getSingleResult().intValue();
    }

    @Override
    public List<Tickets> findAvailableTicketsBySessionId(int sessionId) {
        TypedQuery<Tickets> query = entityManager.createQuery(
                "SELECT t FROM Tickets t WHERE t.purchase.sessions.id = :sessionId AND t.status = 'в наличии'",
                Tickets.class);
        query.setParameter("sessionId", sessionId);
        return query.getResultList();
    }

    @Override
    public List<Integer> findAllSessionIds() {
        TypedQuery<Integer> query = entityManager.createQuery(
                "SELECT DISTINCT t.purchase.sessions.id FROM Tickets t",
                Integer.class);
        return query.getResultList();
    }

    @Override
    public List<Tickets> findReservedTickets() {
        TypedQuery<Tickets> query = entityManager.createQuery(
                "SELECT t FROM Tickets t WHERE t.status = 'зарезервирован'",
                Tickets.class);
        return query.getResultList();
    }

    @Override
    public Tickets findAvailableTicketById(int ticketId) {
        TypedQuery<Tickets> query = entityManager.createQuery(
                "SELECT t FROM Tickets t WHERE t.id = :ticketId AND t.status = 'в наличии'",
                Tickets.class);
        query.setParameter("ticketId", ticketId);
        List<Tickets> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Optional<Tickets> findById(Integer ticketId) {
        return Optional.ofNullable(entityManager.find(Tickets.class, ticketId));
    }

    @Override
    public void save(Tickets ticket) {
        if (entityManager.contains(ticket)) {
            entityManager.merge(ticket);
        } else {
            entityManager.persist(ticket);
        }
    }
}