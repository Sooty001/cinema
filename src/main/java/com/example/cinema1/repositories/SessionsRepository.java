package com.example.cinema1.repositories;

import com.example.cinema1.domain.Sessions;
import java.util.List;

public interface SessionsRepository {
    List<Sessions> findSessionsByUserId(int userId);
}