package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.UserDao;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.CacheUtil;

public class UserCache extends Cache {

    private final String USER_BY_USER_ID_CACHE = "user_by_user_id_cache";

    private UserDao userDao = new UserDao();

    public int countByObject_idAndUser_account(String object_id, String user_account) {
        return userDao.countByObject_idAndUser_account(object_id, user_account);
    }

    public int countByObject_idAndUser_phone(String object_id, String user_phone) {
        return userDao.countByObject_idAndUser_phone(object_id, user_phone);
    }

    public User find(String user_id) {
        User user = CacheUtil.get(USER_BY_USER_ID_CACHE, user_id);

        if (user == null) {
            user = userDao.find(user_id);

            CacheUtil.put(USER_BY_USER_ID_CACHE, user_id, user);
        }

        return user;
    }

    public User findByUser_accountAndUser_passwordAndUser_type(String user_account, String user_password, String user_type) {
        return userDao.findByUser_accountAndUser_passwordAndUser_type(user_account, user_password, user_type);
    }

    public User findByUser_phoneAndUser_passwordAndUser_type(String user_phone, String user_password, String user_type) {
        return userDao.findByUser_accountAndUser_passwordAndUser_type(user_phone, user_password, user_type);
    }

    public User findByWechat_open_idAndWechat_union_idAndUser_type(String wechat_open_id, String wechat_union_id, String user_type) {
        return userDao.findByWechat_open_idAndWechat_union_idAndUser_type(wechat_open_id, wechat_union_id, user_type);
    }

    public User saveByUser_idAndUser_accountAndUser_passwordAndObject_idAndUser_type(String user_id, String user_account, String user_password, String object_id, String user_type, String request_user_id) {
        return userDao.saveByUser_idAndUser_accountAndUser_passwordAndObject_idAndUser_type(user_id, user_account, user_password, object_id, user_type, request_user_id);
    }

    public User saveByUser_idAndUser_phoneAndUser_passwordAndObject_idAndUser_type(String user_id, String user_phone, String user_password, String object_id, String user_type, String request_user_id) {
        return userDao.saveByUser_idAndUser_phoneAndUser_passwordAndObject_idAndUser_type(user_id, user_phone, user_password, object_id, user_type, request_user_id);
    }

    public User saveByUser_idAndUser_nameAndUser_avatarAndWechat_open_idAndWechat_union_idAndObject_idAndUser_type(String user_id, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String object_id, String user_type, String request_user_id) {
        return userDao.saveByUser_idAndUser_nameAndUser_avatarAndWechat_open_idAndWechat_union_idAndObject_idAndUser_type(user_id, user_name, user_avatar, wechat_open_id, wechat_union_id, object_id, user_type, request_user_id);
    }

    public boolean updateByUser_password(String user_password, String request_user_id) {
        return userDao.updateByUser_password(user_password, request_user_id);
    }

    public boolean updateByObject_idAndUser_accountAndUser_type(String object_id, String user_account, String user_type, String request_user_id) {
        return userDao.updateByObject_idAndUser_accountAndUser_type(object_id, user_account, user_type, request_user_id);
    }

    public boolean updateByObject_idAndUser_phoneAndUser_type(String object_id, String user_phone, String user_type, String request_user_id) {
        return userDao.updateByObject_idAndUser_phoneAndUser_type(object_id, user_phone, user_type, request_user_id);
    }

    public boolean updateByObject_idAndUser_passwordAndUser_type(String object_id, String user_password, String user_type, String request_user_id) {
        return userDao.updateByObject_idAndUser_passwordAndUser_type(object_id, user_password, user_type, request_user_id);
    }

    public boolean updateByUser_idAndUser_nameAndUser_avatar(String user_id, String user_name, String user_avatar, String request_user_id) {
        CacheUtil.remove(USER_BY_USER_ID_CACHE, user_id);

        return userDao.updateByUser_idAndUser_nameAndUser_avatar(user_id, user_name, user_avatar, request_user_id);
    }

    public boolean deleteByObject_idAndUser_type(String object_id, String user_type, String request_user_id) {
        return userDao.deleteByObject_idAndUser_type(object_id, user_type, request_user_id);
    }

    public boolean deleteByUser_type(String user_type, String request_user_id) {
        return userDao.deleteByUser_type(user_type, request_user_id);
    }

}
