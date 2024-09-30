package com.example.cinema1.repositories;

import com.example.cinema1.domain.Movies;
import java.util.List;

public interface MoviesRepository {
    List<Movies> findSessionsByUserId(int userId);
    List<Movies> findByGenreAndNotWatchedByUser(String genre, int userId);
}