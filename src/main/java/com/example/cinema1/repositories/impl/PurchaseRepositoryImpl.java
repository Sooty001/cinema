package com.example.cinema1.repositories.impl;

import com.example.cinema1.relationships.Purchase;
import com.example.cinema1.repositories.BaseRepository;
import com.example.cinema1.repositories.PurchaseRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepositoryImpl extends BaseRepository<Purchase, Integer> implements PurchaseRepository {

    public PurchaseRepositoryImpl() {
        super(Purchase.class);
    }

    @Override
    public List<Purchase> findByUsersId(int userId) {
        try {
            TypedQuery<Purchase> query = entityManager.createQuery(
                    "SELECT p FROM Purchase p WHERE p.users.id = :userId", Purchase.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Purchase findByTicketsId(int ticketsId) {
        try {
            TypedQuery<Purchase> query = entityManager.createQuery(
                    "SELECT p FROM Purchase p WHERE p.tickets.id = :ticketsId", Purchase.class);
            query.setParameter("ticketsId", ticketsId);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}