package com.example.cinema1.repositories;

import com.example.cinema1.domain.Halls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

public interface HallsRepository {
    Integer findHallSeatsBySessionId(int sessionId);
    // Можете добавить другие необходимые методы
}