package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Movies;
import com.example.cinema1.repositories.BaseRepository;
import com.example.cinema1.repositories.MoviesRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoviesRepositoryImpl extends BaseRepository<Movies, Integer> implements MoviesRepository {

    public MoviesRepositoryImpl() {
        super(Movies.class);
    }

    @Override
    public List<Movies> findByGenreAndNotWatchedByUser(String genre, int userId) {
        try {
            TypedQuery<Movies> query = entityManager.createQuery(
                    "SELECT m FROM Movies m WHERE m.genre = :genre AND m.id NOT IN (SELECT p.movies.id FROM Pass p JOIN Purchase pu ON p.sessions.id = pu.sessions.id WHERE pu.users.id = :userId)",
                    Movies.class);
            query.setParameter("genre", genre);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}