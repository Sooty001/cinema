package com.example.cinema1.repositories.impl;

import com.example.cinema1.repositories.HallsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class HallsRepositoryImpl implements HallsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer findHallSeatsBySessionId(int sessionId) {
        String query = "SELECT h.seats FROM Pass p JOIN p.halls h WHERE p.sessions.id = :sessionId";
        return entityManager.createQuery(query, Integer.class)
                .setParameter("sessionId", sessionId)
                .getSingleResult();
    }
}