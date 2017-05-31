package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.RoleDao;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.List;

public class RoleCache extends Cache {

    private final String ROLE_BY_ROLE_ID_CACHE = "role_by_role_id_cache";

    private RoleDao roleDao = new RoleDao();

    public int count(String role_name) {
        return roleDao.count(role_name);
    }

    public List<Role> list(String role_name, Integer m, Integer n) {
        return roleDao.list(role_name, m, n);
    }

    public Role find(String role_id) {
        Role role = CacheUtil.get(ROLE_BY_ROLE_ID_CACHE, role_id);

        if (role == null) {
            role = roleDao.find(role_id);

            CacheUtil.put(ROLE_BY_ROLE_ID_CACHE, role_id, role);
        }

        return role;
    }

    public Role save(Role role, String request_user_id) {
        return roleDao.save(role, request_user_id);
    }

    public boolean update(Role role, String request_user_id) {
        CacheUtil.remove(ROLE_BY_ROLE_ID_CACHE, role.getRole_id());

        return roleDao.update(role, request_user_id);
    }

    public boolean delete(String role_id, String request_user_id) {
        CacheUtil.remove(ROLE_BY_ROLE_ID_CACHE, role_id);

        return roleDao.delete(role_id, request_user_id);
    }

}