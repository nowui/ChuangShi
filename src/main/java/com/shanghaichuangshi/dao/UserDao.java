package com.shanghaichuangshi.dao;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class UserDao extends Dao {

    private final String USER_CACHE = "user_cache";

    private String generatePassword(String user_password) {
        return HashKit.sha512(Constant.PRIVATE_KEY + user_password);
    }

    public int countByObject_idAndUser_account(String object_id, String user_account) {
        JMap map = JMap.create();
        map.put(User.OBJECT_ID, object_id);
        map.put(User.USER_ACCOUNT, user_account);
        SqlPara sqlPara = Db.getSqlPara("user.countByObject_idAndUser_account", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public int countByObject_idAndUser_phone(String object_id, String user_phone) {
        JMap map = JMap.create();
        map.put(User.OBJECT_ID, object_id);
        map.put(User.USER_PHONE, user_phone);
        SqlPara sqlPara = Db.getSqlPara("user.countByObject_idAndUser_phone", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public User find(String user_id) {
        User user = CacheUtil.get(USER_CACHE, user_id);

        if (user == null) {
            JMap map = JMap.create();
            map.put(User.USER_ID, user_id);
            SqlPara sqlPara = Db.getSqlPara("user.find", map);

            List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
            if (userList.size() == 0) {
                user = null;
            } else {
                user = userList.get(0);

                CacheUtil.put(USER_CACHE, user_id, user);
            }
        }

        return user;
    }

    public User findByUser_accountAndUser_passwordAndUser_type(String user_account, String user_password, String user_type) {
        JMap map = JMap.create();
        map.put(User.USER_ACCOUNT, user_account);
        map.put(User.USER_PASSWORD, generatePassword(user_password));
        map.put(User.USER_TYPE, user_type);
        SqlPara sqlPara = Db.getSqlPara("user.findByUser_accountAndUser_passwordAndUser_type", map);

        List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public User findByUser_phoneAndUser_passwordAndUser_type(String user_phone, String user_password, String user_type) {
        JMap map = JMap.create();
        map.put(User.USER_PHONE, user_phone);
        map.put(User.USER_PASSWORD, generatePassword(user_password));
        map.put(User.USER_TYPE, user_type);
        SqlPara sqlPara = Db.getSqlPara("user.findByUser_phoneAndUser_passwordAndUser_type", map);

        List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public User findByWechat_open_idAndUser_type(String wechat_open_id, String user_type) {
        JMap map = JMap.create();
        map.put(User.WECHAT_OPEN_ID, wechat_open_id);
        map.put(User.USER_TYPE, user_type);
        SqlPara sqlPara = Db.getSqlPara("user.findByWechat_open_idAndUser_type", map);

        List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public User saveByUser_idAndUser_accountAndUser_passwordAndObject_idAndUser_type(String user_id, String user_account, String user_password, String object_id, String user_type, String request_user_id) {
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_account(user_account);
        user.setUser_password(generatePassword(user_password));
        user.setUser_name(user_account);
        user.setUser_avatar("");
        user.setUser_avatar_thumbnail("");
        user.setUser_avatar_original("");
        user.setWechat_open_id("");
        user.setObject_id(object_id);
        user.setUser_type(user_type);
        user.setSystem_create_user_id(request_user_id);
        user.setSystem_create_time(new Date());
        user.setSystem_update_user_id(request_user_id);
        user.setSystem_update_time(new Date());
        user.setSystem_status(true);
        user.save();

        return user;
    }

    public User saveByUser_idAndUser_phoneAndUser_passwordAndObject_idAndUser_type(String user_id, String user_phone, String user_password, String object_id, String user_type, String request_user_id) {
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_phone(user_phone);
        user.setUser_account("");
        user.setUser_password(generatePassword(user_password));
        user.setUser_name(user_phone);
        user.setUser_avatar("");
        user.setUser_avatar_thumbnail("");
        user.setUser_avatar_original("");
        user.setWechat_open_id("");
        user.setObject_id(object_id);
        user.setUser_type(user_type);
        user.setSystem_create_user_id(request_user_id);
        user.setSystem_create_time(new Date());
        user.setSystem_update_user_id(request_user_id);
        user.setSystem_update_time(new Date());
        user.setSystem_status(true);
        user.save();

        return user;
    }

    public User saveByUser_idAndUser_nameAndUser_avatarAndWechat_open_id(String user_id, String user_name, String user_avatar, String wechat_open_id, String object_id, String user_type, String request_user_id) {
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_phone("");
        user.setUser_account("");
        user.setUser_password("");
        user.setUser_name(user_name);
        user.setUser_avatar(user_avatar);
        user.setUser_avatar_thumbnail(user_avatar);
        user.setUser_avatar_original(user_avatar);
        user.setWechat_open_id(wechat_open_id);
        user.setObject_id(object_id);
        user.setUser_type(user_type);
        user.setSystem_create_user_id(request_user_id);
        user.setSystem_create_time(new Date());
        user.setSystem_update_user_id(request_user_id);
        user.setSystem_update_time(new Date());
        user.setSystem_status(true);
        user.save();

        return user;
    }

    public boolean updateByUser_password(String user_password, String request_user_id) {
        JMap map = JMap.create();
        map.put(User.USER_ID, request_user_id);
        map.put(User.USER_PASSWORD, generatePassword(user_password));
        map.put(User.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(User.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("user.updateByUser_password", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public boolean updateByObject_idAndUser_accountAndUser_type(String object_id, String user_account, String user_type, String request_user_id) {
        JMap map = JMap.create();
        map.put(User.OBJECT_ID, object_id);
        map.put(User.USER_ACCOUNT, user_account);
        map.put(User.USER_TYPE, user_type);
        map.put(User.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(User.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("user.updateByObject_idAndUser_accountAndUser_type", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public boolean updateByObject_idAndUser_phoneAndUser_type(String object_id, String user_phone, String user_type, String request_user_id) {
        JMap map = JMap.create();
        map.put(User.OBJECT_ID, object_id);
        map.put(User.USER_PHONE, user_phone);
        map.put(User.USER_TYPE, user_type);
        map.put(User.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(User.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("user.updateByObject_idAndUser_phoneAndUser_type", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public boolean updateByObject_idAndUser_passwordAndUser_type(String object_id, String user_password, String user_type, String request_user_id) {
        user_password = generatePassword(user_password);

        JMap map = JMap.create();
        map.put(User.OBJECT_ID, object_id);
        map.put(User.USER_PASSWORD, user_password);
        map.put(User.USER_TYPE, user_type);
        map.put(User.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(User.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("user.updateByObject_idAndUser_passwordAndUser_type", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public boolean deleteByObject_idAndUser_type(String object_id, String user_type, String request_user_id) {
        JMap map = JMap.create();
        map.put(User.OBJECT_ID, object_id);
        map.put(User.USER_TYPE, user_type);
        map.put(User.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(User.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("user.deleteByObject_idAndUser_type", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public boolean deleteByUser_type(String user_type, String request_user_id) {
        JMap map = JMap.create();
        map.put(User.USER_TYPE, user_type);
        map.put(User.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(User.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("user.deleteByUser_type", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}
