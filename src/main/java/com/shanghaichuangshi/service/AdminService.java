package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.AdminDao;
import com.shanghaichuangshi.model.Admin;

import java.util.List;

public class AdminService extends Service {

    private final AdminDao adminDao = new AdminDao();

    public int count(Admin admin) {
        return adminDao.count();
    }

    public List<Admin> list(Admin admin) {
        return adminDao.list(admin.getAdmin_name(), admin.getM(), admin.getN());
    }

    public Admin find(Admin admin) {
        return adminDao.find(admin.getAdmin_id());
    }

    public void save(Admin admin) {
        adminDao.save(admin);
    }

    public void update(Admin admin) {
        adminDao.update(admin);
    }

    public void delete(Admin admin) {
        adminDao.delete(admin);
    }

}