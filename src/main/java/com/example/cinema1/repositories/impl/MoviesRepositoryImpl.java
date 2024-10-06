package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Movies;
import com.example.cinema1.repositories.MoviesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoviesRepositoryImpl implements MoviesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movies> findMoviesByUserId(int userId) {
        String query = "SELECT m FROM Pass pa " +
                "JOIN pa.movies m " +
                "JOIN pa.sessions s " +
                "JOIN s.purchase pu " +
                "WHERE pu.users.id = :userId";
        return entityManager.createQuery(query, Movies.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Movies> findByGenreAndNotWatchedByUser(String genre, int userId) {
        String query = "SELECT m FROM Movies m WHERE m.genre = :genre AND m.id NOT IN" +
                "(SELECT pa.movies.id FROM Pass pa JOIN pa.movies m JOIN pa.sessions s JOIN s.purchase pu WHERE pu.users.id = :userId)";
        return entityManager.createQuery(query, Movies.class)
                .setParameter("genre", genre)
                .setParameter("userId", userId)
                .getResultList();
    }
}
