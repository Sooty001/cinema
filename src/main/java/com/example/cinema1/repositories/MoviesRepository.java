package com.example.cinema1.repositories;

import com.example.cinema1.domain.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    List<Movies> findByGenreAndNotWatchedByUser(String genre, int userId);
}