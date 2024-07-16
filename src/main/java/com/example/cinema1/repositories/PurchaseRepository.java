package com.example.cinema1.repositories;

import com.example.cinema1.relationships.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    List<Purchase> findByUsersId(int userId);

    Purchase findByTicketsId(int ticketsId);
}