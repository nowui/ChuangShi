package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.UserDao;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.Util;

public class UserService extends Service {

    private final UserDao userDao = new UserDao();

    public User find(String user_id) {
        return userDao.find(user_id);
    }

    public User findByUser_accountAndUser_passwordAndUser_type(String user_account, String user_password, String user_type) {
        User user = userDao.findByUser_accountAndUser_passwordAndUser_type(user_account, user_password, user_type);

        if (user == null) {
            throw new RuntimeException("帐号或者密码不正确");
        }

        return user;
    }

    public User findByUser_phoneAndUser_passwordAndUser_type(String user_phone, String user_password, String user_type) {
        User user = userDao.findByUser_phoneAndUser_passwordAndUser_type(user_phone, user_password, user_type);

        if (user == null) {
            throw new RuntimeException("帐号或者密码不正确");
        }

        return user;
    }

    public User findByWechat_open_idAndWechat_union_idAndUser_type(String wechat_open_id, String wechat_union_id, String user_type) {
        return userDao.findByWechat_open_idAndWechat_union_idAndUser_type(wechat_open_id, wechat_union_id, user_type);
    }

    public User saveByUser_idAndUser_accountAndUser_passwordAndObject_idAndUser_type(String user_id, String user_account, String user_password, String object_id, String user_type, String request_user_id) {
        int count = userDao.countByObject_idAndUser_account("", user_account);

        if (count > 0) {
            throw new RuntimeException("帐号已经存在:" + user_account);
        }

        return userDao.saveByUser_idAndUser_accountAndUser_passwordAndObject_idAndUser_type(user_id, user_account, user_password, object_id, user_type, request_user_id);
    }

    public User saveByUser_idAndUser_phoneAndUser_passwordAndObject_idAndUser_type(String user_id, String user_phone, String user_password, String object_id, String user_type, String request_user_id) {
        if (!Util.isPhone(user_phone)) {
            throw new RuntimeException("手机号码格式不对:" + user_phone);
        }

        int count = userDao.countByObject_idAndUser_phone("", user_phone);

        if (count > 0) {
            throw new RuntimeException("手机号码已经存在:" + user_phone);
        }

        return userDao.saveByUser_idAndUser_phoneAndUser_passwordAndObject_idAndUser_type(user_id, user_phone, user_password, object_id, user_type, request_user_id);
    }

    public User saveByUser_idAndUser_nameAndUser_avatarAndWechat_open_idAndWechat_union_idAndObject_idAndUser_type(String user_id, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String object_id, String user_type, String request_user_id) {
        return userDao.saveByUser_idAndUser_nameAndUser_avatarAndWechat_open_idAndWechat_union_idAndObject_idAndUser_type(user_id, user_name, user_avatar, wechat_open_id, wechat_union_id, object_id, user_type, request_user_id);
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

    public boolean updateByObject_idAndUser_phoneAndUser_type(String object_id, String user_phone, String user_type, String request_user_id) {
        if (Util.isNullOrEmpty(user_phone)) {
            return false;
        }

        if (!Util.isPhone(user_phone)) {
            throw new RuntimeException("手机号码格式不对:" + user_phone);
        }

        int count = userDao.countByObject_idAndUser_phone(object_id, user_phone);

        if (count > 0) {
            throw new RuntimeException("已经存在手机号码:" + user_phone);
        }

        return userDao.updateByObject_idAndUser_phoneAndUser_type(object_id, user_phone, user_type, request_user_id);
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
