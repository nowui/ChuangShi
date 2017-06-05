package com.shanghaichuangshi.service;

import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.cache.AdminCache;
import com.shanghaichuangshi.cache.AuthorizationCache;
import com.shanghaichuangshi.cache.CategoryCache;
import com.shanghaichuangshi.cache.UserCache;
import com.shanghaichuangshi.model.Admin;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.type.CategoryType;
import com.shanghaichuangshi.type.UserType;
import com.shanghaichuangshi.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminService extends Service {

    private AdminCache adminCache = new AdminCache();
    private UserCache userCache = new UserCache();
    private AuthorizationCache authorizationCache = new AuthorizationCache();
    private CategoryCache categoryCache = new CategoryCache();

    public int count(String admin_name) {
        return adminCache.count(admin_name);
    }

    public List<Admin> list(String admin_name, int m, int n) {
        return adminCache.list(admin_name, m, n);
    }

    public Admin find(String admin_id) {
        return adminCache.find(admin_id);
    }

    public Admin save(Admin admin, User user, String request_user_id) {
        String user_id = Util.getRandomUUID();
        admin.setUser_id(user_id);

        adminCache.save(admin, request_user_id);

        userCache.saveByUser_idAndUser_accountAndUser_passwordAndObject_idAndUser_type(user_id, user.getUser_account(), user.getUser_password(), admin.getAdmin_id(), UserType.ADMIN.getKey(), request_user_id);

        return admin;
    }

    public boolean update(Admin admin, User user, String request_user_id) {
        boolean result = adminCache.update(admin, request_user_id);

        userCache.updateByObject_idAndUser_accountAndUser_type(admin.getAdmin_id(), user.getUser_account(), UserType.ADMIN.getKey(), request_user_id);

        userCache.updateByObject_idAndUser_passwordAndUser_type(admin.getAdmin_id(), user.getUser_password(), UserType.ADMIN.getKey(), request_user_id);

        return result;
    }

    public boolean delete(Admin admin, String request_user_id) {
        boolean result = adminCache.delete(admin.getAdmin_id(), request_user_id);

        userCache.deleteByObject_idAndUser_type(admin.getAdmin_id(), UserType.ADMIN.getKey(), request_user_id);

        return result;
    }

    public Map<String, Object> login(String user_account, String user_password, String platform, String version, String ip_address, String request_user_id) {
        User user = userCache.findByUser_accountAndUser_passwordAndUser_type(user_account, user_password, UserType.ADMIN.getKey());

        if (user == null) {
            throw new RuntimeException("用户名或者密码不正确");
        }

        Admin admin = adminCache.find(user.getObject_id());

        Authorization authorization = authorizationCache.save(user.getUser_id(), platform, version, ip_address, request_user_id);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Admin.ADMIN_NAME, admin.getAdmin_name());
        resultMap.put(Constant.TOKEN.toLowerCase(), authorization.getAuthorization_token());

        return resultMap;
    }

    public List<Map<String, Object>> menu(String request_user_id) {
        return categoryCache.treeListByCategory_key(CategoryType.RESOURCE.getKey(), Category.CATEGORY_VALUE, Category.CATEGORY_REMARK);
    }

}