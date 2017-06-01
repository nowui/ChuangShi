package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.RoleDao;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.List;

public class RoleCache extends Cache {

    private final String ROLE_BY_ROLE_ID_CACHE = "role_by_role_id_cache";
    private final String ROLE_BY_ROLE_KEY_CACHE = "role_by_role_key_cache";

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

            CacheUtil.put(ROLE_BY_ROLE_KEY_CACHE, role.getRole_key(), role);
        }

        return role;
    }

    public Role findByRole_key(String role_key) {
        Role role = CacheUtil.get(ROLE_BY_ROLE_KEY_CACHE, role_key);

        if (role == null) {
            role = roleDao.findByRole_key(role_key);

            CacheUtil.put(ROLE_BY_ROLE_KEY_CACHE, role_key, role);
        }

        return role;
    }

    public Role save(Role role, String request_user_id) {
        return roleDao.save(role, request_user_id);
    }

    public boolean update(Role role, String request_user_id) {
        Role cache = CacheUtil.get(ROLE_BY_ROLE_ID_CACHE, role.getRole_id());
        if (cache != null) {
            CacheUtil.remove(ROLE_BY_ROLE_KEY_CACHE, cache.getRole_key());
        }

        CacheUtil.remove(ROLE_BY_ROLE_ID_CACHE, role.getRole_id());

        return roleDao.update(role, request_user_id);
    }

    public boolean delete(String role_id, String request_user_id) {
        Role cache = CacheUtil.get(ROLE_BY_ROLE_ID_CACHE, role_id);
        if (cache != null) {
            CacheUtil.remove(ROLE_BY_ROLE_KEY_CACHE, cache.getRole_key());
        }

        CacheUtil.remove(ROLE_BY_ROLE_ID_CACHE, role_id);

        return roleDao.delete(role_id, request_user_id);
    }

}