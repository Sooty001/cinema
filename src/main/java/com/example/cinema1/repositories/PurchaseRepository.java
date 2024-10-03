package com.example.cinema1.repositories;

import com.example.cinema1.domain.Purchase;
import java.util.List;

public interface PurchaseRepository {
    Purchase findByTicketsId(int ticketsId);
    List<Integer> findAllSessionIds();
}