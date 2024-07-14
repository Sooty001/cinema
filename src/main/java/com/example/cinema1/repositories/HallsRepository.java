package com.example.cinema1.repositories;

import com.example.cinema1.domain.Halls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallsRepository extends JpaRepository<Halls, Integer> {

}