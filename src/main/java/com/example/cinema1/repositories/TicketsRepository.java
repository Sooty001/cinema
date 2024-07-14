package com.example.cinema1.repositories;

import com.example.cinema1.domain.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Integer> {

    @Query("select t.place from Tickets t where t.price = :money")
    List<Object> findAllTicketsByPrice(@Param("money") int money);

}