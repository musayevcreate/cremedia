
package com.cremedia.cremedia.service.Impl;
import com.cremedia.cremedia.dao.UserDao;
import com.cremedia.cremedia.models.User;
import com.cremedia.cremedia.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User get(Long userId) {
        return userDao.get(userId);
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public void delete(Long userId) {
        userDao.delete(userId);
    }

}
