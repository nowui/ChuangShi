package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Role;

import java.util.List;

public class RoleDao extends Dao {

    private int count(Role role) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Role.TABLE_ROLE);
        dynamicSQL.append("WHERE ").append(Role.TABLE_ROLE).append(".").append(Role.SYSTEM_STATUS).append(" = 1 ");

        return role.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public int count() {
        Role role = new Role();

        return count(role);
    }

    private List<Role> list(Role role, Integer m, Integer n, String... columns) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(role.packageSelect(columns));
        dynamicSQL.append("FROM ").append(Role.TABLE_ROLE).append(" ");
        dynamicSQL.append("WHERE ").append(Role.TABLE_ROLE).append(".").append(Role.SYSTEM_STATUS).append(" = 1 ");
        dynamicSQL.append("ORDER BY ").append(Role.TABLE_ROLE).append(".").append(Role.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.appendPagination(m, n);

        return role.list(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Role> list(Integer m, Integer n, String... columns) {
        Role role = new Role();

        return list(role, m, n, columns);
    }

    private Role find(Role role, String... columns) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(role.packageSelect(columns));
        dynamicSQL.append("FROM ").append(Role.TABLE_ROLE).append(" ");
        dynamicSQL.append("WHERE ").append(Role.TABLE_ROLE).append(".").append(Role.SYSTEM_STATUS).append(" = 1 ");

        return role.find(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public Role findByRole_Id(String role_id, String... columns) {
        return new Role().findById(role_id, columns);
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