package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.AdminDao;
import com.shanghaichuangshi.model.Admin;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.List;

public class AdminCache extends Cache {

    private final String ADMIN_BY_ADMIN_ID_CACHE = "admin_by_admin_id_cache";

    private AdminDao adminDao = new AdminDao();

    public int count(String admin_name) {
        return adminDao.count(admin_name);
    }

    public List<Admin> list(String admin_name, int m, int n) {
        return adminDao.list(admin_name, m, n);
    }

    public Admin find(String admin_id) {
        Admin admin = CacheUtil.get(ADMIN_BY_ADMIN_ID_CACHE, admin_id);

        if (admin == null) {
            admin = adminDao.find(admin_id);

            CacheUtil.put(ADMIN_BY_ADMIN_ID_CACHE, admin_id, admin);
        }

        return admin;
    }

    public Admin save(Admin admin, String request_user_id) {
        return adminDao.save(admin, request_user_id);
    }

    public boolean update(Admin admin, String request_user_id) {
        CacheUtil.remove(ADMIN_BY_ADMIN_ID_CACHE, admin.getAdmin_id());

        return adminDao.update(admin, request_user_id);
    }

    public boolean delete(String admin_id, String request_user_id) {
        CacheUtil.remove(ADMIN_BY_ADMIN_ID_CACHE, admin_id);

        return adminDao.delete(admin_id, request_user_id);
    }

}
