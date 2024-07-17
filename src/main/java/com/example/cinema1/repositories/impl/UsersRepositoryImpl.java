package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Users;
import com.example.cinema1.repositories.BaseRepository;
import com.example.cinema1.repositories.UsersRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl extends BaseRepository<Users, Integer> implements UsersRepository {

    public UsersRepositoryImpl() {
        super(Users.class);
    }

    @Override
    public Optional<Users> findById(int userId) {
        return Optional.ofNullable(entityManager.find(Users.class, userId));
    }
}