package com.example.cinema1.services;

import com.example.cinema1.Dto.MoviesDto;
import com.example.cinema1.domain.Movies;
import com.example.cinema1.domain.Sessions;
import com.example.cinema1.relationships.Pass;
import com.example.cinema1.relationships.Purchase;
import com.example.cinema1.repositories.MoviesRepository;
import com.example.cinema1.repositories.PurchaseRepository;
import com.example.cinema1.repositories.SessionsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final PurchaseRepository purchaseRepository;
    private final SessionsRepository sessionsRepository;
    private final MoviesRepository moviesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersService(PurchaseRepository purchaseRepository, SessionsRepository sessionsRepository, MoviesRepository moviesRepository, ModelMapper modelMapper) {
        this.purchaseRepository = purchaseRepository;
        this.sessionsRepository = sessionsRepository;
        this.moviesRepository = moviesRepository;
        this.modelMapper = modelMapper;
    }

    public List<MoviesDto> usersMovies(int userId) {
        List<Purchase> purchases = purchaseRepository.findByUsersId(userId);

        Map<String, Integer> genreCount = new HashMap<>();
        Set<Integer> watchedMovieIds = new HashSet<>();

        for (Purchase purchase : purchases) {
            List<Sessions> sessions = sessionsRepository.findSessionsByUserId(userId);
            for (Sessions session : sessions) {
                for (Pass pass : session.getPass()) {
                    Movies movie = pass.getMovies();
                    watchedMovieIds.add(movie.getId());
                    String genre = movie.getGenre();
                    genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
                }
            }
        }

        List<String> preferredGenres = genreCount.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<MoviesDto> recommendations = new ArrayList<>();
        for (String genre : preferredGenres) {
            List<Movies> movies = moviesRepository.findByGenreAndNotWatchedByUser(genre, userId);
            for (Movies movie : movies) {
                if (!watchedMovieIds.contains(movie.getId())) {
                    MoviesDto movieDto = modelMapper.map(movie, MoviesDto.class);
                    recommendations.add(movieDto);
                }
            }
            if (!recommendations.isEmpty()) {
                break;
            }
        }

        return recommendations;
    }
}