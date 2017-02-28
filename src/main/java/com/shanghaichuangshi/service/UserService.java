package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.UserDao;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.Util;

public class UserService extends Service {

    private static final UserDao userDao = new UserDao();

    public String saveByUser_accountAndUser_passwordAndObject_idAndUser_type(String user_account, String user_password, String object_id, String user_type, String request_user_id) {
        int count = userDao.countByObject_idAndUser_account("", user_account);

        if (count > 0) {
            throw new RuntimeException("帐号已经存在:" + user_account);
        }

        User user = userDao.saveByUser_accountAndUser_passwordAndObject_idAndUser_type(user_account, user_password, object_id, user_type, request_user_id);

        return user.getUser_id();
    }

    public String saveByUser_phoneAndUser_passwordAndObject_idAndUser_type(String user_phone, String user_password, String object_id, String user_type, String request_user_id) {
        int count = userDao.countByObject_idAndUser_phone("", user_phone);

        if (count > 0) {
            throw new RuntimeException("手机号码已经存在:" + user_phone);
        }

        User user = userDao.saveByUser_phoneAndUser_passwordAndObject_idAndUser_type(user_phone, user_password, object_id, user_type, request_user_id);

        return user.getUser_id();
    }

    public User findByUser_accountAndUser_passwordAndUser_type(String user_account, String user_password, String user_type) {
        User user = userDao.findByUser_accountAndUser_passwordAndUser_type(user_account, user_password, user_type);

        if (user == null) {
            throw new RuntimeException("用户名或者密码不正确");
        }

        return user;
    }

    public boolean updateByUser_password(String user_password, String request_user_id) {
        return userDao.updateByUser_password(user_password, request_user_id);
    }

    public boolean updateByObject_idAndUser_accountAndUser_type(String object_id, String user_account, String user_type, String request_user_id) {
        if (Util.isNullOrEmpty(user_account)) {
            return false;
        }

        int count = userDao.countByObject_idAndUser_account(object_id, user_account);

        if (count > 0) {
            throw new RuntimeException("已经存在帐号:" + user_account);
        }

        return userDao.updateByObject_idAndUser_accountAndUser_type(object_id, user_account, user_type, request_user_id);
    }

    public boolean updateByObject_idAndUser_passwordAndUser_type(String object_id, String user_password, String user_type, String request_user_id) {
        if (Util.isNullOrEmpty(user_password)) {
            return false;
        }

        return userDao.updateByObject_idAndUser_passwordAndUser_type(object_id, user_password, user_type, request_user_id);
    }

    public boolean deleteByObject_idAndUser_type(String object_id, String user_type, String request_user_id) {
        return userDao.deleteByObject_idAndUser_type(object_id, user_type, request_user_id);
    }

    public boolean deleteByUser_type(String user_type, String request_user_id) {
        return userDao.deleteByUser_type(user_type, request_user_id);
    }

}
