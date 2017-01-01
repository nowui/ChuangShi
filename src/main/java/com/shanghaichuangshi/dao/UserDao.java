package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.DatabaseUtil;

import java.util.List;

public class UserDao extends Dao {

    public int count() {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(User.TABLE_USER);
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<User> list(Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(User.TABLE_USER).append(".* ");
        dynamicSQL.append("FROM ").append(User.TABLE_USER).append(" ");
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");
        dynamicSQL.append("ORDER BY ").append(User.TABLE_USER).append(".").append(User.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.appendPagination(m, n);

        return (List<User>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), User.class);
    }

    public User find(String user_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(User.TABLE_USER).append(".* ");
        dynamicSQL.append("FROM ").append(User.TABLE_USER).append(" ");
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");
        dynamicSQL.append("AND ").append(User.TABLE_USER).append(".").append(User.USER_ID).append(" = ? ", user_id);

        return (User) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), User.class);
    }

    public boolean save(User user) {
        return user.save();
    }

    public boolean update(User user) {
        return user.update();
    }

    public boolean delete(User user) {
        return user.delete();
    }

}