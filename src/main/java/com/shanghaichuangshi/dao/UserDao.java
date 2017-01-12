package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.Date;

public class UserDao extends Dao {

    public int countByUser_accountAndObject_id(String user_account, String object_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(User.TABLE_USER).append(" ");
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(User.TABLE_USER).append(".").append(User.USER_ACCOUNT).append(" = ? ", user_account);
        if (!Util.isNullOrEmpty(object_id)) {
            dynamicSQL.append("AND ").append(User.TABLE_USER).append(".").append(User.OBJECT_ID).append(" != ? ", object_id);
        }

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

//    public List<User> list(String user_name, Integer m, Integer n) {
//        DynamicSQL dynamicSQL = new DynamicSQL();
//
//        dynamicSQL.append("SELECT ");
//        dynamicSQL.append(User.TABLE_USER).append(".").append(User.USER_ID).append(", ");
//        dynamicSQL.append(User.TABLE_USER).append(".").append(User.USER_NAME).append(" ");
//        dynamicSQL.append("FROM ").append(User.TABLE_USER).append(" ");
//        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = ? ", true);
//        if (!Util.isNullOrEmpty(user_name)) {
//            dynamicSQL.append("AND ").append(User.TABLE_USER).append(".").append(User.USER_NAME).append(" LIKE ? ", "%" + user_name + "%");
//        }
//        dynamicSQL.append("ORDER BY ").append(User.TABLE_USER).append(".").append(User.SYSTEM_CREATE_TIME).append(" DESC ");
//        dynamicSQL.append("LIMIT ?, ? ", m, n);
//
//        return (List<User>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), User.class);
//    }

    public User findByUser_accountAndUser_passwordAndUser_type(String user_account, String user_password, String user_type) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(User.TABLE_USER).append(".* ");
        dynamicSQL.append("FROM ").append(User.TABLE_USER).append(" ");
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(User.TABLE_USER).append(".").append(User.USER_ACCOUNT).append(" = ? ", user_account);
        dynamicSQL.append("AND ").append(User.TABLE_USER).append(".").append(User.USER_PASSWORD).append(" = ? ", user_password);
        dynamicSQL.append("AND ").append(User.TABLE_USER).append(".").append(User.USER_TYPE).append(" = ? ", user_type);

        return (User) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), User.class);
    }

    public String saveByUser_accountAndUser_passwordAndObject_idAndUser_type(String user_account, String user_password, String object_id, String user_type, String request_user_id) {
        User user = new User();

        user.setUser_account(user_account);
        user.setUser_password(user_password);
        user.setObject_id(object_id);
        user.setUser_type(user_type);
        user.setRequest_user_id(request_user_id);

        user.save();

        return user.getUser_id();
    }

    public boolean update(User user) {
        return user.update();
    }

    public boolean updateByUser_accountAndObject_id(String user_account, String object_id, String request_user_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("UPDATE ").append(User.TABLE_USER).append(" SET ");
        dynamicSQL.append(User.USER_ACCOUNT).append(" = ?, ", user_account);
        dynamicSQL.append(User.SYSTEM_UPDATE_USER_ID).append(" = ?, ", request_user_id);
        dynamicSQL.append(User.SYSTEM_UPDATE_TIME).append(" = ? ", new Date());
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.OBJECT_ID).append(" = ? ", object_id);

        return DatabaseUtil.update(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public boolean updateByUser_passwordAndObject_id(String user_password, String object_id, String request_user_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("UPDATE ").append(User.TABLE_USER).append(" SET ");
        dynamicSQL.append(User.USER_PASSWORD).append(" = ?, ", user_password);
        dynamicSQL.append(User.SYSTEM_UPDATE_USER_ID).append(" = ?, ", request_user_id);
        dynamicSQL.append(User.SYSTEM_UPDATE_TIME).append(" = ? ", new Date());
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.OBJECT_ID).append(" = ? ", object_id);

        return DatabaseUtil.update(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public boolean deleteByObject_id(String object_id, String request_user_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("UPDATE ").append(User.TABLE_USER).append(" SET ");
        dynamicSQL.append(User.SYSTEM_UPDATE_USER_ID).append(" = ?, ", request_user_id);
        dynamicSQL.append(User.SYSTEM_UPDATE_TIME).append(" = ?, ", new Date());
        dynamicSQL.append(User.SYSTEM_STATUS).append(" = ? ", false);
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.OBJECT_ID).append(" = ? ", object_id);

        return DatabaseUtil.update(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

}