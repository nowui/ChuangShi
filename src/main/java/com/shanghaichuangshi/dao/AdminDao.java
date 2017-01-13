package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Admin;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class AdminDao extends Dao {

    public int count() {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Admin.TABLE_ADMIN).append(" ");
        dynamicSQL.append("WHERE ").append(Admin.TABLE_ADMIN).append(".").append(Admin.SYSTEM_STATUS).append(" = ? ", true);

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Admin> list(String admin_name, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Admin.TABLE_ADMIN).append(".").append(Admin.ADMIN_ID).append(", ");
        dynamicSQL.append(Admin.TABLE_ADMIN).append(".").append(Admin.ADMIN_NAME).append(", ");
        dynamicSQL.append(User.TABLE_USER).append(".").append(User.USER_ACCOUNT).append(" ");
        dynamicSQL.append("FROM ").append(Admin.TABLE_ADMIN).append(" ");
        dynamicSQL.append("LEFT JOIN ").append(User.TABLE_USER).append(" ON ").append(User.TABLE_USER).append(".").append(User.USER_ID).append(" = ").append(Admin.TABLE_ADMIN).append(".").append(Admin.USER_ID).append(" ");
        dynamicSQL.append("WHERE ").append(Admin.TABLE_ADMIN).append(".").append(Admin.SYSTEM_STATUS).append(" = ? ", true);
        if (!Util.isNullOrEmpty(admin_name)) {
            dynamicSQL.append("AND ").append(Admin.TABLE_ADMIN).append(".").append(Admin.ADMIN_NAME).append(" LIKE ? ", "%" + admin_name + "%");
        }
        dynamicSQL.append("ORDER BY ").append(Admin.TABLE_ADMIN).append(".").append(Admin.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.append("LIMIT ?, ? ", m, n);

        return (List<Admin>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Admin.class);
    }

    public Admin find(String admin_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Admin.TABLE_ADMIN).append(".*, ");
        dynamicSQL.append(User.TABLE_USER).append(".").append(User.USER_ACCOUNT).append(" ");
        dynamicSQL.append("FROM ").append(Admin.TABLE_ADMIN).append(" ");
        dynamicSQL.append("LEFT JOIN ").append(User.TABLE_USER).append(" ON ").append(User.TABLE_USER).append(".").append(User.USER_ID).append(" = ").append(Admin.TABLE_ADMIN).append(".").append(Admin.USER_ID).append(" ");
        dynamicSQL.append("WHERE ").append(Admin.TABLE_ADMIN).append(".").append(Admin.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(Admin.TABLE_ADMIN).append(".").append(Admin.ADMIN_ID).append(" = ? ", admin_id);

        return (Admin) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Admin.class);
    }

    public Admin findByUser_id(String user_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Admin.TABLE_ADMIN).append(".* ");
        dynamicSQL.append("FROM ").append(Admin.TABLE_ADMIN).append(" ");
        dynamicSQL.append("WHERE ").append(Admin.TABLE_ADMIN).append(".").append(Admin.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(Admin.TABLE_ADMIN).append(".").append(Admin.USER_ID).append(" = ? ", user_id);

        return (Admin) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Admin.class);
    }

    public String save(Admin admin) {
        admin.setAdmin_id(Util.getRandomUUID());

        admin.save();

        return admin.getAdmin_id();
    }

    public boolean update(Admin admin) {
        return admin.update();
    }

    public boolean updateByAdmin_idAndUser_id(String admin_id, String user_id, String request_user_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("UPDATE ").append(Admin.TABLE_ADMIN).append(" SET ");
        dynamicSQL.append(Admin.USER_ID).append(" = ?, ", user_id);
        dynamicSQL.append(Admin.SYSTEM_UPDATE_USER_ID).append(" = ?, ", request_user_id);
        dynamicSQL.append(Admin.SYSTEM_UPDATE_TIME).append(" = ? ", new Date());
        dynamicSQL.append("WHERE ").append(Admin.TABLE_ADMIN).append(".").append(Admin.ADMIN_ID).append(" = ? ", admin_id);

        return DatabaseUtil.update(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public boolean delete(Admin admin) {
        return admin.delete();
    }

}