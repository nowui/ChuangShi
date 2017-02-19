package com.shanghaichuangshi.service;

import com.jfinal.template.ext.directive.Str;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.dao.AdminDao;
import com.shanghaichuangshi.model.Admin;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.type.CategoryType;
import com.shanghaichuangshi.type.UserType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminService extends Service {

    private final AdminDao adminDao = new AdminDao();

    private final UserService userService = new UserService();
    private final AuthorizationService authorizationService = new AuthorizationService();
    private final CategoryService categoryService = new CategoryService();

    public int count(Admin admin) {
        return adminDao.count(admin.getAdmin_name());
    }

    public List<Admin> list(Admin admin, int m, int n) {
        return adminDao.list(admin.getAdmin_name(), m, n);
    }

    public Admin find(Admin admin) {
        return adminDao.find(admin.getAdmin_id());
    }

    public Admin findByUser_id(String user_id) {
        return adminDao.findByUser_id(user_id);
    }

    public void save(Admin admin, User user, String request_user_id) {
        Admin a = adminDao.save(admin, request_user_id);

        String user_id = userService.saveByUser_accountAndUser_passwordAndObject_idAndUser_type(user.getUser_account(), user.getUser_password(), a.getAdmin_id(), UserType.ADMIN.getKey(), request_user_id);

       adminDao.updateByAdmin_idAndUser_id(a.getAdmin_id(), user_id, request_user_id);
    }

    public void update(Admin admin, User user, String request_user_id) {
        adminDao.update(admin, request_user_id);

        userService.updateByUser_idAndUser_account(admin.getUser_id(), user.getUser_account(), request_user_id);

        userService.updateByUser_idAndUser_password(admin.getUser_id(), user.getUser_password(), request_user_id);
    }

    public void delete(Admin admin, String request_user_id) {
        adminDao.delete(admin.getAdmin_id(), request_user_id);

        userService.deleteByObject_idAndUser_type(admin.getAdmin_id(), UserType.ADMIN.getKey(), request_user_id);
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