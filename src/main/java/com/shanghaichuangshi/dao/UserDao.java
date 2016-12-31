package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.User;

import java.util.List;

public class UserDao extends Dao {

    private int count(User user) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(User.TABLE_USER);
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");

        return user.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public int count() {
        User user = new User();

        return count(user);
    }

    private List<User> list(User user, Integer m, Integer n, String... columns) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(user.packageSelectSQL(columns));
        dynamicSQL.append("FROM ").append(User.TABLE_USER).append(" ");
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");
        dynamicSQL.append("ORDER BY ").append(User.TABLE_USER).append(".").append(User.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.appendPagination(m, n);

        return user.list(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<User> list(Integer m, Integer n, String... columns) {
        User user = new User();

        return list(user, m, n, columns);
    }

    private User find(User user, String... columns) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(user.packageSelectSQL(columns));
        dynamicSQL.append("FROM ").append(User.TABLE_USER).append(" ");
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");

        return user.find(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public User findByUser_Id(String user_id, String... columns) {
        return new User().findById(user_id, columns);
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