package com.example.cinema1.repositories;

import com.example.cinema1.relationships.Purchase;
import java.util.List;

public interface PurchaseRepository {
    List<Purchase> findByUsersId(int userId);
    Purchase findByTicketsId(int ticketsId);
}