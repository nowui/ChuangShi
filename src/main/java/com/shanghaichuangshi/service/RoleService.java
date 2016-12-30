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
        return roleDao.list(role.getPage_index(), role.getPage_size(), role.getSelectList().toArray(new String[role.getSelectList().size()]));
    }

    public Role find(Role role) {
        return roleDao.findByRole_Id(role.getRole_id(), role.getSelectList().toArray(new String[role.getSelectList().size()]));
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