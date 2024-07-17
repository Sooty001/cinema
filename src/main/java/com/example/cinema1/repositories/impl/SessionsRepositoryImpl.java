package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Sessions;
import com.example.cinema1.repositories.BaseRepository;
import com.example.cinema1.repositories.SessionsRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionsRepositoryImpl extends BaseRepository<Sessions, Integer> implements SessionsRepository {

    public SessionsRepositoryImpl() {
        super(Sessions.class);
    }

    @Override
    public List<Sessions> findSessionsByUserId(int userId) {
        try {
            TypedQuery<Sessions> query = entityManager.createQuery(
                    "SELECT s FROM Sessions s JOIN Purchase p ON s.id = p.sessions.id WHERE p.users.id = :userId",
                    Sessions.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}