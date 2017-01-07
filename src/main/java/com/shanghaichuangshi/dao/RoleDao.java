package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.List;

public class RoleDao extends Dao {

    public int count() {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Role.TABLE_ROLE).append(" ");
        dynamicSQL.append("WHERE ").append(Role.TABLE_ROLE).append(".").append(Role.SYSTEM_STATUS).append(" = 1 ");

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Role> list(String role_name, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Role.TABLE_ROLE).append(".").append(Role.ROLE_ID).append(", ");
        dynamicSQL.append(Role.TABLE_ROLE).append(".").append(Role.ROLE_NAME).append(" ");
        dynamicSQL.append("FROM ").append(Role.TABLE_ROLE).append(" ");
        dynamicSQL.append("WHERE ").append(Role.TABLE_ROLE).append(".").append(Role.SYSTEM_STATUS).append(" = 1 ");
        if (!Util.isNullOrEmpty(role_name)) {
            dynamicSQL.append("AND ").append(Role.TABLE_ROLE).append(".").append(Role.ROLE_NAME).append(" LIKE ? ", "%" + role_name + "%");
        }
        dynamicSQL.append("ORDER BY ").append(Role.TABLE_ROLE).append(".").append(Role.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.append("LIMIT ?, ? ", m, n);

        return (List<Role>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Role.class);
    }

    public Role find(String role_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Role.TABLE_ROLE).append(".* ");
        dynamicSQL.append("FROM ").append(Role.TABLE_ROLE).append(" ");
        dynamicSQL.append("WHERE ").append(Role.TABLE_ROLE).append(".").append(Role.SYSTEM_STATUS).append(" = 1 ");
        dynamicSQL.append("AND ").append(Role.TABLE_ROLE).append(".").append(Role.ROLE_ID).append(" = ? ", role_id);

        return (Role) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Role.class);
    }

    public boolean save(Role role) {
        return role.save();
    }

    public boolean update(Role role) {
        return role.update();
    }

    public boolean delete(Role role) {
        return role.delete();
    }

}