package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Sessions;
import com.example.cinema1.repositories.SessionsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SessionsRepositoryImpl implements SessionsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Sessions> findSessionsByUserId(int userId) {
        TypedQuery<Sessions> query = entityManager.createQuery(
                "SELECT s FROM Sessions s JOIN Purchase p ON s.id = p.sessions.id WHERE p.users.id = :userId",
                Sessions.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}