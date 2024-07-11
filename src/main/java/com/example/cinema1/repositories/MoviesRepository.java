package com.example.cinema1.repositories;

import com.example.cinema1.domain.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    List<Movies> findAllByGenre(String genre);

    List<Movies> findAllByRatingGreaterThan(float rating);


    @Query(value = "select m from Movies m where m.rating > :rating")
    List<Movies> findMoviesByRatingGreaterThan(float rating);


    @Query(value = "select m from Movies m where m.genre = :genre and m.releaseYear = :releaseYear")
    List<Movies> findMoviesByGenreAndReleaseYear(String genre, int releaseYear);
}