package com.cremedia.cremedia.dao;
import com.cremedia.cremedia.models.User2;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao {
    void add(User2 user2);
    void update(User2 user2);
    User2 get(Long userId);
    void delete(Long userId);
}