package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.UserDao;
import com.shanghaichuangshi.model.User;

import java.util.List;
import java.util.Map;

public class UserService extends Service {

    private UserDao userDao = new UserDao();

    public List<User> list(User user) {
        return userDao.list(user.getPage_index(), user.getPage_size());
    }

    public List<User> listForAdmin(User user) {
        return userDao.list(user.getPage_index(), user.getPage_size());
    }

    public User find(User user) {
        User userMap = new User();

        return userDao.findByUser_Id(userMap.getUser_id());
    }

    public User findForAdmin(User user) {
        return userDao.findByUser_Id(user.getUser_id());
    }

    public void save(User user) {
        user.save();
    }

    public void update(User user) {

    }

    public void delete(User user) {

    }

}
