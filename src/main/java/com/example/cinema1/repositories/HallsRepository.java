package com.example.cinema1.repositories;

import com.example.cinema1.domain.Halls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallsRepository extends JpaRepository<Halls, Integer> {

    List<Halls> findAllByType(String type);

    List<Halls> findAllBySeats(int seats);

    List<Halls> findAllByHallNumber(int hallNumber);


    @Query(value = "select h from Halls h where h.seats > :seats")
    List<Halls> findHallsBySeatsGreaterThan(int seats);
}