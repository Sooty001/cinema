package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Users;
import com.example.cinema1.repositories.UsersRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Users> findById(int userId) {
        return Optional.ofNullable(entityManager.find(Users.class, userId));
    }
}