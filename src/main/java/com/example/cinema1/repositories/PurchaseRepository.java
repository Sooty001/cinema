package com.example.cinema1.repositories;

import com.example.cinema1.domain.Purchase;

public interface PurchaseRepository {
    Purchase findByTicketsId(int ticketsId);
}