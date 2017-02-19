package com.shanghaichuangshi.dao;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class UserDao extends Dao {

    private String generatePassword(String user_password) {
        return HashKit.sha512(Constant.PRIVATE_KEY + user_password);
    }

    public int countByUser_idAndUser_account(String user_id, String user_account) {
        JMap map = JMap.create();
        map.put(User.USER_ID, user_id);
        map.put(User.USER_ACCOUNT, user_account);
        SqlPara sqlPara = Db.getSqlPara("user.countByUser_idAndUser_account", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
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

    public User saveByUser_accountAndUser_passwordAndObject_idAndUser_type(String user_account, String user_password, String object_id, String user_type, String request_user_id) {
        User user = new User();
        user.setUser_id(Util.getRandomUUID());
        user.setUser_account(user_account);
        user.setUser_password(generatePassword(user_password));
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

    public boolean updateByUser_idAndUser_account(String user_id, String user_account, String request_user_id) {
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_account(user_account);
        user.remove(User.SYSTEM_CREATE_USER_ID);
        user.remove(User.SYSTEM_CREATE_TIME);
        user.setSystem_update_user_id(request_user_id);
        user.setSystem_update_time(new Date());
        user.remove(User.SYSTEM_STATUS);

        return user.update();
    }

    public boolean updateByUser_idAndUser_password(String user_id, String user_password, String request_user_id) {
        user_password = generatePassword(user_password);

        User user = new User();
        user.setUser_id(user_id);
        user.setUser_password(user_password);
        user.remove(User.SYSTEM_CREATE_USER_ID);
        user.remove(User.SYSTEM_CREATE_TIME);
        user.setSystem_update_user_id(request_user_id);
        user.setSystem_update_time(new Date());
        user.remove(User.SYSTEM_STATUS);

        return user.update();
    }

    public boolean deleteByObject_idAndUser_type(String object_id, String user_type, String request_user_id) {
        JMap map = JMap.create();
        map.put(User.OBJECT_ID, object_id);
        map.put(User.USER_TYPE, user_type);
        map.put(User.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(User.SYSTEM_UPDATE_TIME, new Date());
        map.put(User.SYSTEM_STATUS, false);
        SqlPara sqlPara = Db.getSqlPara("user.deleteByObject_idAndUser_type", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) == 1;
    }

}
