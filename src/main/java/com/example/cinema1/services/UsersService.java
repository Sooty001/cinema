package com.example.cinema1.services;

import com.example.cinema1.dto.MoviesDto;
import com.example.cinema1.domain.Movies;
import com.example.cinema1.domain.Sessions;
import com.example.cinema1.domain.Pass;
import com.example.cinema1.repositories.MoviesRepository;
import com.example.cinema1.repositories.SessionsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersService {

    private final SessionsRepository sessionsRepository;
    private final MoviesRepository moviesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersService(SessionsRepository sessionsRepository, MoviesRepository moviesRepository, ModelMapper modelMapper) {
        this.sessionsRepository = sessionsRepository;
        this.moviesRepository = moviesRepository;
        this.modelMapper = modelMapper;
    }

    public List<MoviesDto> usersMovies(int userId) {
        Map<String, Integer> genreCount = new HashMap<>();

        List<Sessions> sessions = sessionsRepository.findSessionsByUserId(userId);
        for (Sessions session : sessions) {
            for (Pass pass : session.getPass()) {
                Movies movie = pass.getMovies();
                String genre = movie.getGenre();
                genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
            }
        }

        List<String> preferredGenres = genreCount.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .map(Map.Entry::getKey)
                .toList();

        List<MoviesDto> recommendations = new ArrayList<>();
        for (String genre : preferredGenres) {
            List<Movies> movies = moviesRepository.findByGenreAndNotWatchedByUser(genre, userId);
            for (Movies movie : movies) {
                MoviesDto movieDto = modelMapper.map(movie, MoviesDto.class);
                recommendations.add(movieDto);
            }
            if (!recommendations.isEmpty()) {
                break;
            }
        }
        return recommendations;
    }
}