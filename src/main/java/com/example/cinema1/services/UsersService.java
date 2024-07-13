package com.example.cinema1.services;

import com.example.cinema1.domain.Users;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Service
public class UsersService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveAllUsers(List<Users> users) {
        for (Users user : users) {
            entityManager.persist(user);
        }
    }
}

