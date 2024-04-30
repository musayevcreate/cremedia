package com.cremedia.cremedia.dao;
import com.cremedia.cremedia.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface UserDao {
    void add(User user);
    void update(User user);
    User get(Long userId);
    void delete(Long userId);
}