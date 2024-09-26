package com.example.cinema1.repositories.impl;

import com.example.cinema1.domain.Purchase;
import com.example.cinema1.repositories.PurchaseRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {

    private final JdbcTemplate jdbc;

    public PurchaseRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Purchase findByTicketsId(int ticketsId) {
        String sql = "SELECT * FROM Purchase p WHERE p.id_ticket = ?";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(Purchase.class), ticketsId);
    }

    @Override
    public List<Integer> findAllSessionIds() {
        String sql = "SELECT DISTINCT p.id_session FROM Purchase p";
        return jdbc.query(sql, (rs, rowNum) -> rs.getInt("id_session"));
    }

    @Override
    public void update(Purchase purchase) {
        String sql = "UPDATE purchase SET id_user = ? WHERE id = ?";
        jdbc.update(sql, purchase.getUsers().getId(), purchase.getId());
    }
}