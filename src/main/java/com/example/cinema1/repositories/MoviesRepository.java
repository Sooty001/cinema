package com.example.cinema1.repositories;

import com.example.cinema1.domain.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {
    // Получение всех фильмов по определенному жанру, которые пользователь еще не смотрел
    @Query("SELECT m FROM Movies m WHERE m.genre = :genre AND m.id NOT IN (SELECT p.movies.id FROM Pass p JOIN Purchase pu ON p.sessions.id = pu.sessions.id WHERE pu.users.id = :userId)")
    List<Movies> findByGenreAndNotWatchedByUser(@Param("genre") String genre, @Param("userId") int userId);
}