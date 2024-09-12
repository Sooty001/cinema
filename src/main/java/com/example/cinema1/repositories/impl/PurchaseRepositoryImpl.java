package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Purchase;
import com.example.cinema1.repositories.PurchaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Purchase findByTicketsId(int ticketsId) {
        TypedQuery<Purchase> query = entityManager.createQuery(
                "SELECT p FROM Purchase p WHERE p.tickets.id = :ticketsId", Purchase.class);
        query.setParameter("ticketsId", ticketsId);
        return query.getSingleResult();
    }
}