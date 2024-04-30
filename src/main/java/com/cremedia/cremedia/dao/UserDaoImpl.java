package com.cremedia.cremedia.dao;

import com.cremedia.cremedia.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void add(User user) {
        String query = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, user.getName(), user.getSurname(), user.getEmail());
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(query, user.getName(), user.getSurname(), user.getEmail(), user.getId());
    }

    @Override
    public User get(Long userId) {
        return null;
    }


    @Override
    public void delete(Long userId) {
        String query = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(query, userId);
    }
}
