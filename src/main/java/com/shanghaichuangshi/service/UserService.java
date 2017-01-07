package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.UserDao;
import com.shanghaichuangshi.model.User;

import java.util.List;

public class UserService extends Service {

    private final UserDao userDao = new UserDao();

    public int count(User user) {
        return userDao.count();
    }

    public List<User> list(User user) {
        return userDao.list(user.getM(), user.getN());
    }

    public User find(User user) {
        return userDao.find(user.getUser_id());
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

}