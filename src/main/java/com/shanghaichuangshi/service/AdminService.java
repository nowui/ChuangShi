package com.shanghaichuangshi.service;

import com.shanghaichuangshi.constant.Key;
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
        return adminDao.count();
    }

    public List<Admin> list(Admin admin) {
        return adminDao.list(admin.getAdmin_name(), admin.getM(), admin.getN());
    }

    public Admin find(Admin admin) {
        return adminDao.find(admin.getAdmin_id());
    }

    public Admin findByUser_id(String user_id) {
        return adminDao.findByUser_id(user_id);
    }

    public void save(Admin admin, User user) {
        String admin_id = adminDao.save(admin);

        String user_id = userService.saveByUser_accountAndUser_passwordAndObject_idAndUser_type(user.getUser_account(), user.getUser_password(), admin_id, UserType.ADMIN.getKey(), admin.getRequest_user_id());

       adminDao.updateByAdmin_idAndUser_id(admin_id, user_id, admin.getRequest_user_id());
    }

    public void update(Admin admin, User user) {
        adminDao.update(admin);

        userService.updateByUser_idAndUser_account(admin.getUser_id(), user.getUser_account(), admin.getRequest_user_id());

        userService.updateByUser_idAndUser_password(admin.getUser_id(), user.getUser_password(), admin.getRequest_user_id());
    }

    public void delete(Admin admin) {
        adminDao.delete(admin);

        userService.deleteByObject_idAndUser_type(admin.getAdmin_id(), UserType.ADMIN.getKey(), admin.getRequest_user_id());
    }

    public Map<String, Object> login(User user) {
        User u = userService.findByUser_accountAndUser_passwordAndUser_type(user.getUser_account(), user.getUser_password(), UserType.ADMIN.getKey());

        Admin admin = adminDao.findByUser_id(u.getUser_id());

        String token = authorizationService.saveByUser_id(u.getUser_id());

        Category category = categoryService.treeListByCategory_key(CategoryType.MENU.getKey());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Admin.ADMIN_NAME, admin.getAdmin_name());
        resultMap.put(Key.TOKEN, token);
        resultMap.put(Key.MENU, category.get(Key.CHILDREN));

        return resultMap;
    }

}