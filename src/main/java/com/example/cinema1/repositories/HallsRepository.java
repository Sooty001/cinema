package com.example.cinema1.repositories;

import com.example.cinema1.domain.Halls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface HallsRepository extends JpaRepository<Halls, Integer> {

    // Поиск количества мест в зале по идентификатору сеанса.
    @Query("SELECT h.seats FROM Halls h JOIN Pass p ON h.id = p.halls.id WHERE p.sessions.id = :sessionId")
    Integer findHallSeatsBySessionId(@Param("sessionId") int sessionId);
}