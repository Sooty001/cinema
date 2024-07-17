package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Halls;
import com.example.cinema1.repositories.BaseRepository;
import com.example.cinema1.repositories.HallsRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;


@Repository
public class HallsRepositoryImpl extends BaseRepository<Halls, Integer> implements HallsRepository {

    public HallsRepositoryImpl() {
        super(Halls.class);
    }

    @Override
    public Integer findHallSeatsBySessionId(int sessionId) {
        try {
            TypedQuery<Integer> query = entityManager.createQuery(
                    "SELECT h.seats FROM Halls h JOIN Pass p ON h.id = p.halls.id WHERE p.sessions.id = :sessionId",
                    Integer.class);
            query.setParameter("sessionId", sessionId);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Реализуйте другие необходимые методы
}