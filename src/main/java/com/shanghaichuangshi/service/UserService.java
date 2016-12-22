package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.UserDao;
import com.shanghaichuangshi.model.User;

import java.util.List;
import java.util.Map;

public class UserService extends Service {

    private UserDao userDao = new UserDao();

    public List<User> list(Map<String, Object> map) {
        return userDao.list();
    }

    public List<User> listForAdmin(Map<String, Object> map) {
        return userDao.list();
    }

    public User find(Map<String, Object> map) {
        User userMap = new User();

        return userDao.findById(userMap.getUser_id());
    }

    public User findForAdmin(Map<String, Object> map) {
        User userMap = new User();

        return userDao.findById(userMap.getUser_id());
    }

    public void save(User user) {
        user.save();
    }

    public void update(User user) {

    }

    public void delete(User user) {

    }

}
