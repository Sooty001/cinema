package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Tickets;
import com.example.cinema1.repositories.TicketsRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketsRepositoryImpl implements TicketsRepository {

    private final JdbcTemplate jdbc;

    public TicketsRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Integer countSoldTicketsBySessionId(int sessionId) {
        String sql = "SELECT COUNT(*) FROM Tickets t JOIN Purchase p ON t.id = p.id_ticket WHERE p.id_session = ? AND t.status = 'продан'";
        return jdbc.queryForObject(sql, Long.class, sessionId).intValue();
    }

    @Override
    public List<Tickets> findAvailableTicketsBySessionId(int sessionId) {
        String sql = "SELECT * FROM Tickets t JOIN Purchase p ON t.id = p.id_ticket WHERE p.id_session = ? AND t.status = 'в наличии'";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Tickets.class), sessionId);
    }

    @Override
    public List<Tickets> findReservedTickets() {
        String sql = "SELECT * FROM Tickets t WHERE t.status = 'зарезервирован'";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Tickets.class));
    }

    @Override
    public Tickets findAvailableTicketById(int ticketId) {
        String sql = "SELECT * FROM Tickets t WHERE t.id = ? AND t.status = 'в наличии'";
        List<Tickets> tickets = jdbc.query(sql, new BeanPropertyRowMapper<>(Tickets.class), ticketId);
        return tickets.isEmpty() ? null : tickets.get(0);
    }

    @Override
    public Optional<Tickets> findById(Integer ticketId) {
        String sql = "select * from tickets where id = ?";
        List<Tickets> tickets = jdbc.query(sql, new BeanPropertyRowMapper<>(Tickets.class), ticketId);
        return tickets.isEmpty() ? Optional.empty() : Optional.of(tickets.get(0));
    }

    @Override
    public void update(Tickets ticket) {
        String sql = "UPDATE tickets SET price = ?, status = ?, choice = ? WHERE id = ?";
        jdbc.update(sql, ticket.getPrice(), ticket.getStatus(), ticket.getChoice(), ticket.getId());
    }
}