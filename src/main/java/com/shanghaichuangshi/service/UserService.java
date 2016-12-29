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
        return userDao.list(user.getPage_index(), user.getPage_size(), user.getSelectList().toArray(new String[user.getSelectList().size()]));
    }

    public User find(User user) {
        return userDao.findByUser_Id(user.getUser_id(), user.getSelectList().toArray(new String[user.getSelectList().size()]));
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
