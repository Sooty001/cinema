package com.example.cinema1.controllers;

import com.example.cinema1.dto.MoviesDto;
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

    @GetMapping("/rec/{userId}")
    public List<MoviesDto> getUsersMovies(@PathVariable("userId") int userId) {
        return usersService.usersMovies(userId);
    }
}