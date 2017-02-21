package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.RoleDao;
import com.shanghaichuangshi.model.Role;

import java.util.List;

public class RoleService extends Service {

    private static final RoleDao roleDao = new RoleDao();

    public int count(Role role) {
        return roleDao.count(role.getRole_name());
    }

    public List<Role> list(Role role, int m, int n) {
        return roleDao.list(role.getRole_name(), m, n);
    }

    public Role find(Role role) {
        return roleDao.find(role.getRole_id());
    }

    public void save(Role role, String request_user_id) {
        roleDao.save(role, request_user_id);
    }

    public void update(Role role, String request_user_id) {
        roleDao.update(role, request_user_id);
    }

    public void delete(Role role, String request_user_id) {
        roleDao.delete(role.getRole_id(), request_user_id);
    }

}