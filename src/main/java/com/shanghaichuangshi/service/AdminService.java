package com.shanghaichuangshi.service;

import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.dao.AdminDao;
import com.shanghaichuangshi.model.Admin;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.type.CategoryType;
import com.shanghaichuangshi.type.UserType;
import com.shanghaichuangshi.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminService extends Service {

    private AdminDao adminDao = new AdminDao();

    private UserService userService = new UserService();
    private AuthorizationService authorizationService = new AuthorizationService();
    private CategoryService categoryService = new CategoryService();

    public int count(Admin admin) {
        return adminDao.count(admin.getAdmin_name());
    }

    public List<Admin> list(Admin admin, int m, int n) {
        return adminDao.list(admin.getAdmin_name(), m, n);
    }

    public Admin find(String admin_id) {
        return adminDao.find(admin_id);
    }

    public Admin findByUser_id(String user_id) {
        return adminDao.findByUser_id(user_id);
    }

    public Admin save(Admin admin, User user, String request_user_id) {
        String user_id = Util.getRandomUUID();
        admin.setUser_id(user_id);

        adminDao.save(admin, request_user_id);

        userService.saveByUser_idAndUser_accountAndUser_passwordAndObject_idAndUser_type(user_id, user.getUser_account(), user.getUser_password(), admin.getAdmin_id(), UserType.ADMIN.getKey(), request_user_id);

        return admin;
    }

    public boolean update(Admin admin, User user, String request_user_id) {
        boolean result = adminDao.update(admin, request_user_id);

        userService.updateByObject_idAndUser_accountAndUser_type(admin.getAdmin_id(), user.getUser_account(), UserType.ADMIN.getKey(), request_user_id);

        userService.updateByObject_idAndUser_passwordAndUser_type(admin.getAdmin_id(), user.getUser_password(), UserType.ADMIN.getKey(), request_user_id);

        return result;
    }

    public boolean delete(Admin admin, String request_user_id) {
        boolean result = adminDao.delete(admin.getAdmin_id(), request_user_id);

        userService.deleteByObject_idAndUser_type(admin.getAdmin_id(), UserType.ADMIN.getKey(), request_user_id);

        return result;
    }

    public Map<String, Object> login(User user, String platform, String version, String ip_address, String request_user_id) {
        User u = userService.findByUser_accountAndUser_passwordAndUser_type(user.getUser_account(), user.getUser_password(), UserType.ADMIN.getKey());

        Admin admin = adminDao.findByUser_id(u.getUser_id());

        String token = authorizationService.saveByUser_id(u.getUser_id(), platform, version, ip_address, request_user_id);

        Category category = categoryService.treeListByCategory_key(CategoryType.MENU.getKey());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Admin.ADMIN_NAME, admin.getAdmin_name());
        resultMap.put(Constant.TOKEN.toLowerCase(), token);
        resultMap.put(Constant.MENU, category.get(Constant.CHILDREN));

        return resultMap;
    }

}