package com.example.cinema1.repositories.impl;

import com.example.cinema1.repositories.HallsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class HallsRepositoryImpl implements HallsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer findHallSeatsBySessionId(int sessionId) {
        TypedQuery<Integer> query = entityManager.createQuery(
                "SELECT h.seats FROM Halls h JOIN Pass p ON h.id = p.halls.id WHERE p.sessions.id = :sessionId",
                Integer.class);
        query.setParameter("sessionId", sessionId);
        return query.getSingleResult();
    }
}