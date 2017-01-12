package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.AdminDao;
import com.shanghaichuangshi.model.Admin;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.type.UserType;

import java.util.List;

public class AdminService extends Service {

    private final AdminDao adminDao = new AdminDao();

    private final UserService userService = new UserService();

    public int count(Admin admin) {
        return adminDao.count();
    }

    public List<Admin> list(Admin admin) {
        return adminDao.list(admin.getAdmin_name(), admin.getM(), admin.getN());
    }

    public Admin find(Admin admin) {
        return adminDao.find(admin.getAdmin_id());
    }

    public void save(Admin admin, User user) {
        String admin_id = adminDao.save(admin);

        String user_id = userService.saveByUser_accountAndUser_passwordAndObject_idAndUser_type(user.getUser_account(), user.getUser_password(), admin_id, UserType.ADMIN.getKey(), admin.getRequest_user_id());

       adminDao.updateByAdmin_idAndUser_id(admin_id, user_id, admin.getRequest_user_id());
    }

    public void update(Admin admin, User user) {
        adminDao.update(admin);

        userService.updateByUser_accountAndObject_id(user.getUser_account(), admin.getAdmin_id(), admin.getRequest_user_id());

        userService.updateByUser_passwordAndObject_id(user.getUser_password(), admin.getAdmin_id(), admin.getRequest_user_id());
    }

    public void delete(Admin admin) {
        adminDao.delete(admin);
    }

}