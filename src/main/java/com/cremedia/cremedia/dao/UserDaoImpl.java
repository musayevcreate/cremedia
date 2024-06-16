package com.cremedia.cremedia.dao;

import com.cremedia.cremedia.models.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void add(User2 user2) {
        String query = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, user2.getName(), user2.getSurname(), user2.getEmail());
    }

    @Override
    public void update(User2 user2) {
        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(query, user2.getName(), user2.getSurname(), user2.getEmail(), user2.getId());
    }

    @Override
    public User2 get(Long userId) {
        return null;
    }


    @Override
    public void delete(Long userId) {
        String query = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(query, userId);
    }
}
