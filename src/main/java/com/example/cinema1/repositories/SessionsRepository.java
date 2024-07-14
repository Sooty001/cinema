package com.example.cinema1.repositories;

import com.example.cinema1.domain.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SessionsRepository extends JpaRepository<Sessions, Integer> {
    // Получение сеансов, на которые ходил пользователь
    @Query("SELECT s FROM Sessions s JOIN Purchase p ON s.id = p.sessions.id WHERE p.users.id = :userId")
    List<Sessions> findSessionsByUserId(@Param("userId") int userId);

}