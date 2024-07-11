package com.example.cinema1.repositories;

import com.example.cinema1.domain.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SessionsRepository extends JpaRepository<Sessions, Integer> {


    @Query(value = "select s from Sessions s where s.start_time > :startTime")
    List<Sessions> findSessionsByStartTimeAfter(String startTime);



    @Query(value = "select s from Sessions s where s.date = :date and s.start_time > :startTime")
    List<Sessions> findSessionsByDateAndStartTimeAfter(Date date, String startTime);
}