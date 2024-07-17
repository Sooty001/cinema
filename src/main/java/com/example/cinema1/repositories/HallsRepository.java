package com.example.cinema1.repositories;

public interface HallsRepository {
    Integer findHallSeatsBySessionId(int sessionId);
}