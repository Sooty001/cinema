package com.example.cinema1.controllers;

import com.example.cinema1.domain.Movies;
import com.example.cinema1.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Метод для получения рекомендаций фильмов для пользователя
    @GetMapping("/rec/{userId}")
    public List<Movies> getUsersMovies(@PathVariable("userId") int userId) {
        return usersService.usersMovies(userId);
    }
}