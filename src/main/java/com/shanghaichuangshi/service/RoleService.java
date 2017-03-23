package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.RoleDao;
import com.shanghaichuangshi.model.Role;

import java.util.List;

public class RoleService extends Service {

    private final RoleDao roleDao = new RoleDao();

    public int count(Role role) {
        return roleDao.count(role.getRole_name());
    }

    public List<Role> list(Role role, int m, int n) {
        return roleDao.list(role.getRole_name(), m, n);
    }

    public Role find(String role_id) {
        return roleDao.find(role_id);
    }

    public Role save(Role role, String request_user_id) {
        return roleDao.save(role, request_user_id);
    }

    public boolean update(Role role, String request_user_id) {
        return roleDao.update(role, request_user_id);
    }

    public boolean delete(Role role, String request_user_id) {
        return roleDao.delete(role.getRole_id(), request_user_id);
    }

}