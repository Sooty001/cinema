package com.example.cinema1.repositories;

import com.example.cinema1.domain.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Integer> {

    // Запрос для получения всех билетов по идентификатору сеанса
    @Query("SELECT t FROM Tickets t WHERE t.purchase.sessions.id = :sessionId")
    List<Tickets> findTicketsBySessionId(@Param("sessionId") int sessionId);

    // Запрос для подсчета количества проданных билетов на определенный сеанс
    @Query("SELECT COUNT(t) FROM Tickets t WHERE t.purchase.sessions.id = :sessionId AND t.status = 'продан'")
    Long countSoldTicketsBySessionId(@Param("sessionId") int sessionId);

    // Запрос для получения всех доступных (непроданных) билетов по сеансу
    @Query("SELECT t FROM Tickets t WHERE t.purchase.sessions.id = :sessionId AND t.status = 'в наличии'")
    List<Tickets> findAvailableTicketsBySessionId(@Param("sessionId") int sessionId);

    @Query("SELECT DISTINCT t.purchase.sessions.id FROM Tickets t")
    List<Integer> findAllSessionIds();




    @Query("SELECT t FROM Tickets t WHERE t.status = 'зарезервирован'")
    List<Tickets> findReservedTickets();

    @Query("SELECT t FROM Tickets t WHERE t.id = :ticketId AND t.status = 'в наличии'")
    Tickets findAvailableTicketById(@Param("ticketId") int ticketId);

}