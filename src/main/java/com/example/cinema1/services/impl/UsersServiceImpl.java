package com.example.cinema1.services.impl;

import com.example.cinema1.dto.MoviesDto;
import com.example.cinema1.domain.Movies;
import com.example.cinema1.exceptions.UserNotFoundException;
import com.example.cinema1.repositories.MoviesRepository;
import com.example.cinema1.repositories.UsersRepository;
import com.example.cinema1.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final MoviesRepository moviesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, MoviesRepository moviesRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.moviesRepository = moviesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MoviesDto> usersMovies(int userId) {

        if (usersRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        Map<String, Integer> genreCount = new HashMap<>();

        List<Movies> movies1 = moviesRepository.findSessionsByUserId(userId);

        for (Movies movie : movies1) {
            String genre = movie.getGenre();
            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
        }

        List<String> preferredGenres = new ArrayList<>(genreCount.keySet());
        preferredGenres.sort((genre1, genre2) -> genreCount.get(genre2) - genreCount.get(genre1));

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