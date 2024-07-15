package com.example.cinema1.services;

import com.example.cinema1.domain.Tickets;
import com.example.cinema1.repositories.TicketsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Service
public class TicketsService {

    @PersistenceContext
    private EntityManager entityManager;
    private final TicketsRepository ticketsRepository;

    @Autowired
    public TicketsService(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    public List<Object> getAllTicketsByPrice(int price) {
        return ticketsRepository.findAllTicketsByPrice(price);
    }


//    public List<Tickets> getAllplace() {
//        Query query = entityManager.createQuery("SELECT t FROM Tickets t WHERE t.price > 60");
//        return query.getResultList();
//    }
//
//    public List<Tickets> findCheapTicketsBoughtByTeens() {
//        String jpql = "SELECT t FROM Tickets t JOIN t.purchase p JOIN p.users u WHERE t.price = 50 AND u.age = 18";
//        Query query = entityManager.createQuery(jpql);
//        return query.getResultList();
//    }

}
