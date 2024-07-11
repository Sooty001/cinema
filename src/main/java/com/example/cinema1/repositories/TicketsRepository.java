package com.example.cinema1.repositories;

import com.example.cinema1.domain.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Integer> {


    @Query(value = "select t from Tickets t where t.price > :price")
    List<Tickets> findTicketsByPriceGreaterThan(float price);

}