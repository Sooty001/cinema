package com.example.cinema1.repositories;

import com.example.cinema1.domain.Users;
import java.util.Optional;

public interface UsersRepository {
    Optional<Users> findById(int userId);
    // Добавьте другие методы, если необходимо
}