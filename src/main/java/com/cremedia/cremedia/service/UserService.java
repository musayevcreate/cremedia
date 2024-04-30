package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void update(User user);
    User get(Long userId);
    List<User> getAll();
    void delete(Long userId);
}
