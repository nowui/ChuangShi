package com.shanghaichuangshi.dao;

import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.model.Admin;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class AdminDao extends Dao {

    public int count(String admin_name) {
        JMap map = JMap.create();
        map.put(Admin.ADMIN_NAME, admin_name);
        SqlPara sqlPara = Db.getSqlPara("admin.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Admin> list(String admin_name, int m, int n) {
        JMap map = JMap.create();
        map.put(Admin.ADMIN_NAME, admin_name);
        map.put(Admin.M, m);
        map.put(Admin.N, n);
        SqlPara sqlPara = Db.getSqlPara("admin.list", map);

        return new Admin().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Admin find(String admin_id) {
        JMap map = JMap.create();
        map.put(Admin.ADMIN_ID, admin_id);
        SqlPara sqlPara = Db.getSqlPara("admin.find", map);

        List<Admin> adminList = new Admin().find(sqlPara.getSql(), sqlPara.getPara());
        if (adminList.size() == 0) {
            return null;
        } else {
            return adminList.get(0);
        }
    }

    public Admin findByUser_id(String user_id) {
        JMap map = JMap.create();
        map.put(Admin.USER_ID, user_id);
        SqlPara sqlPara = Db.getSqlPara("admin.findByUser_id", map);

        List<Admin> adminList = new Admin().find(sqlPara.getSql(), sqlPara.getPara());
        if (adminList.size() == 0) {
            return null;
        } else {
            return adminList.get(0);
        }
    }

    public Admin save(Admin admin, String request_user_id) {
        admin.setAdmin_id(Util.getRandomUUID());
        admin.setSystem_create_user_id(request_user_id);
        admin.setSystem_create_time(new Date());
        admin.setSystem_update_user_id(request_user_id);
        admin.setSystem_update_time(new Date());
        admin.setSystem_status(true);
        admin.save();

        return admin;
    }

    public boolean update(Admin admin, String request_user_id) {
        admin.remove(Admin.SYSTEM_CREATE_USER_ID);
        admin.remove(Admin.SYSTEM_CREATE_TIME);
        admin.setSystem_update_user_id(request_user_id);
        admin.setSystem_update_time(new Date());
        admin.remove(Admin.SYSTEM_STATUS);

        return admin.update();
    }

    public boolean delete(String admin_id, String request_user_id) {
        JMap map = JMap.create();
        map.put(Admin.ADMIN_ID, admin_id);
        map.put(Admin.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(Admin.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("admin.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}
