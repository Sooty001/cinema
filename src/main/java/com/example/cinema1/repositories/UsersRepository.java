package com.example.cinema1.repositories;

import com.example.cinema1.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findById(int userId);
}