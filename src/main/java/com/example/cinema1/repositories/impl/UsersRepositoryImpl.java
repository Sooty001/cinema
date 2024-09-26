package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Users;
import com.example.cinema1.repositories.UsersRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private final JdbcTemplate jdbc;

    public UsersRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Users> findById(int userId) {
        String sql = "select * from users where id = ?";
        List<Users> users = jdbc.query(sql, new BeanPropertyRowMapper<>(Users.class), userId);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
}