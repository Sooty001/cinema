package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Movies;
import com.example.cinema1.repositories.MoviesRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MoviesRepositoryImpl implements MoviesRepository {

    private final JdbcTemplate jdbc;

    public MoviesRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Movies> findByGenreAndNotWatchedByUser(String genre, int userId) {
        String sql = "SELECT m.* FROM Movies m WHERE m.genre = ? " +
                "AND m.id NOT IN (SELECT p.id_movies FROM Pass p " +
                "JOIN Purchase pu ON p.id_session = pu.id_session " +
                "WHERE pu.id_user = ?)";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Movies.class), genre, userId);
    }

    @Override
    public List<Movies> findSessionsByUserId(int userId) {
        String sql = "SELECT m.* FROM Pass pa JOIN purchase p ON pa.id_session = p.id_session JOIN movies m ON pa.id_movies = m.id WHERE p.id_user = ?";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Movies.class), userId);
    }
}