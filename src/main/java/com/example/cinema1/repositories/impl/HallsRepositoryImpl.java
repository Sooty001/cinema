package com.example.cinema1.repositories.impl;

import com.example.cinema1.repositories.HallsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HallsRepositoryImpl implements HallsRepository {

    private final JdbcTemplate jdbc;

    public HallsRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Integer findHallSeatsBySessionId(int sessionId) {
        String sql = "SELECT h.seats FROM Halls h JOIN Pass p ON h.id = p.id_hall WHERE p.id_session = ?";
        return jdbc.queryForObject(sql, Integer.class, sessionId);
    }
}