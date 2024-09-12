package com.example.cinema1.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int userId) {
        super("User with id " + userId + " not found");
    }
}