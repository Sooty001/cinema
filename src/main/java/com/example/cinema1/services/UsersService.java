package com.example.cinema1.services;

import com.example.cinema1.dto.MoviesDto;
import com.example.cinema1.exceptions.UserNotFoundException;
import java.util.List;

public interface UsersService {
    List<MoviesDto> usersMovies(int userId) throws UserNotFoundException;
}
