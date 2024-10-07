package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Purchase;
import com.example.cinema1.repositories.PurchaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepositoryImpl extends GenericCrudRepository<Purchase, Integer> implements PurchaseRepository {

    public PurchaseRepositoryImpl(Class<Purchase> entityClass) {
        super(entityClass);
    }

    @Override
    public Purchase findByTicketsId(int ticketsId) {
        String query = "SELECT p FROM Purchase p WHERE p.tickets.id = :ticketsId";
        return entityManager.createQuery(query, Purchase.class)
                .setParameter("ticketsId", ticketsId)
                .getSingleResult();
    }

    @Override
    public List<Integer> findAllSessionIds() {
        String query = "SELECT DISTINCT p.sessions.id FROM Purchase p";
        return entityManager.createQuery(query, Integer.class).getResultList();
    }
}
