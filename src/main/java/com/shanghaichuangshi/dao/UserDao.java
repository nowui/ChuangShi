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

    private String packageSql(List<String> searchList) {
        StringBuffer sql = new StringBuffer();

        if (searchList.size() > 0) {
            for (int i = 0; i < searchList.size(); i++) {
                if (i > 0) {
                    sql.append(", ");
                }

                sql.append(searchList.get(i));
            }
            sql.append(" ");
        } else {
            sql.append(User.TABLE_USER).append(".* ");
        }

        return sql.toString();
    }

    private List<User> list(User user, List<String> searchList, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(packageSql(searchList));
        dynamicSQL.append("FROM ").append(User.TABLE_USER).append(" ");
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");
        dynamicSQL.append("ORDER BY ").append(User.TABLE_USER).append(".").append(User.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.appendPagination(m, n);

        return user.list(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<User> list(List<String> searchList, Integer m, Integer n) {
        User user = new User();

        return list(user, searchList, m, n);
    }

    private User find(User user, List<String> searchList) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(packageSql(searchList));
        dynamicSQL.append("FROM ").append(User.TABLE_USER).append(" ");
        dynamicSQL.append("WHERE ").append(User.TABLE_USER).append(".").append(User.SYSTEM_STATUS).append(" = 1 ");

        return user.find(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public User findByUser_Id(String user_id, List<String> searchList) {
        return new User().findById(user_id, searchList);
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
