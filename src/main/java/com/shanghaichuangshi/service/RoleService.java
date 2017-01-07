package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.RoleDao;
import com.shanghaichuangshi.model.Role;

import java.util.List;

public class RoleService extends Service {

    private final RoleDao roleDao = new RoleDao();

    public int count(Role role) {
        return roleDao.count();
    }

    public List<Role> list(Role role) {
        return roleDao.list(role.getRole_name(), role.getM(), role.getN());
    }

    public Role find(Role role) {
        return roleDao.find(role.getRole_id());
    }

    public void save(Role role) {
        roleDao.save(role);
    }

    public void update(Role role) {
        roleDao.update(role);
    }

    public void delete(Role role) {
        roleDao.delete(role);
    }

}