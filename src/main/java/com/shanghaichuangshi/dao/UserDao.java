package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.DatabaseUtil;

import java.util.List;

public class UserDao extends Dao {

    private int count(User user) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(User.TABLE_USER);
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public int count() {
        User user = new User();

        return count(user);
    }

    private List<User> list(User user, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(User.TABLE_USER).append(".* ");
        dynamicSQL.append("FROM ").append(User.TABLE_USER);
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");
        dynamicSQL.append("ORDER BY ").append(User.TABLE_USER).append(".").append(User.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.appendPagination(m, n);

        return user.list(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<User> list(Integer m, Integer n) {
        User user = new User();

        return list(user, m, n);
    }

    private User find(User user) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(User.TABLE_USER).append(".* ");
        dynamicSQL.append("FROM ").append(User.TABLE_USER);
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");

        return user.find(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public User findById(String user_id) {
        return new User().findById(user_id);
    }

    public boolean save(User user, String request_user_id) {
        return user.save(request_user_id);
    }

    public boolean update(User user, String request_user_id) {
        return user.update(request_user_id);
    }

}
