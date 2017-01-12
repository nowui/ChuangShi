package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.UserDao;
import com.shanghaichuangshi.util.Util;

public class UserService extends Service {

    private final UserDao userDao = new UserDao();

    public String saveByUser_accountAndUser_passwordAndObject_idAndUser_type(String user_account, String user_password, String object_id, String user_type, String request_user_id) {
        int count = userDao.countByUser_accountAndObject_id(user_account, "");

        if (count > 0) {
            throw new RuntimeException("已经存在帐号:" + user_account);
        }

        return userDao.saveByUser_accountAndUser_passwordAndObject_idAndUser_type(user_account, user_password, object_id, user_type, request_user_id);
    }

    public boolean updateByUser_accountAndObject_id(String user_account, String object_id, String request_user_id) {
        if (Util.isNullOrEmpty(user_account)) {
            return false;
        }

        int count = userDao.countByUser_accountAndObject_id(user_account, object_id);

        if (count > 0) {
            throw new RuntimeException("已经存在帐号:" + user_account);
        }

        return userDao.updateByUser_accountAndObject_id(user_account, object_id, request_user_id);
    }

    public boolean updateByUser_passwordAndObject_id(String user_password, String object_id, String request_user_id) {
        if (Util.isNullOrEmpty(user_password)) {
            return false;
        }

        return userDao.updateByUser_passwordAndObject_id(user_password, object_id, request_user_id);
    }

}