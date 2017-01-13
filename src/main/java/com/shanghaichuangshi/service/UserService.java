package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.UserDao;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.type.UserType;
import com.shanghaichuangshi.util.Util;

public class UserService extends Service {

    private final UserDao userDao = new UserDao();

    public String saveByUser_accountAndUser_passwordAndObject_idAndUser_type(String user_account, String user_password, String object_id, String user_type, String request_user_id) {
        int count = userDao.countByUser_idAndUser_account(user_account, "");

        if (count > 0) {
            throw new RuntimeException("已经存在帐号:" + user_account);
        }

        return userDao.saveByUser_accountAndUser_passwordAndObject_idAndUser_type(user_account, user_password, object_id, user_type, request_user_id);
    }

    public User findByUser_accountAndUser_passwordAndUser_type(String user_account, String user_password, String user_type) {
        User user = userDao.findByUser_accountAndUser_passwordAndUser_type(user_account, user_password, user_type);

        if (user == null) {
            throw new RuntimeException("用户名或者密码不正确");
        }

        return user;
    }

    public boolean updateByUser_idAndUser_account(String user_id, String user_account, String request_user_id) {
        if (Util.isNullOrEmpty(user_account)) {
            return false;
        }

        int count = userDao.countByUser_idAndUser_account(user_id, user_account);

        if (count > 0) {
            throw new RuntimeException("已经存在帐号:" + user_account);
        }

        return userDao.updateByUser_idAndUser_account(user_id, user_account, request_user_id);
    }

    public boolean updateByUser_idAndUser_password(String user_id, String user_password, String request_user_id) {
        if (Util.isNullOrEmpty(user_password)) {
            return false;
        }

        return userDao.updateByUser_idAndUser_password(user_id, user_password, request_user_id);
    }

    public boolean deleteByObject_idAndUser_type(String object_id, String user_type, String request_user_id) {
        return userDao.deleteByObject_idAndUser_type(object_id, user_type, request_user_id);
    }

}