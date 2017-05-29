package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.RoleDao;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.type.CategoryType;

import java.util.List;

public class RoleService extends Service {

    private final RoleDao roleDao = new RoleDao();

    private final CategoryService categoryService = new CategoryService();

    public int count(String role_name) {
        return roleDao.count(role_name);
    }

    public List<Role> list(String role_name, int m, int n) {
        return roleDao.list(role_name, m, n);
    }

    public Category categoryList() {
        return categoryService.treeListByCategory_key(CategoryType.ROLE.getKey());
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