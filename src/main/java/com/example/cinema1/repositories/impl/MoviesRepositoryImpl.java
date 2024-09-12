package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Movies;
import com.example.cinema1.repositories.MoviesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MoviesRepositoryImpl implements MoviesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movies> findByGenreAndNotWatchedByUser(String genre, int userId) {
        TypedQuery<Movies> query = entityManager.createQuery(
                "SELECT m FROM Movies m WHERE m.genre = :genre AND m.id NOT IN " +
                        "(SELECT p.movies.id FROM Pass p JOIN Purchase pu ON p.sessions.id = pu.sessions.id WHERE pu.users.id = :userId)",
                Movies.class);
        query.setParameter("genre", genre);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}