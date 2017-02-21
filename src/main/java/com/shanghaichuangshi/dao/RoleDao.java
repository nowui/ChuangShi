package com.shanghaichuangshi.dao;

import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class RoleDao extends Dao {

    public int count(String role_name) {
        JMap map = JMap.create();
        SqlPara sqlPara = Db.getSqlPara("role.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Role> list(String role_name, Integer m, Integer n) {
        JMap map = JMap.create();
        map.put(Role.ROLE_NAME, role_name);
        map.put(Authorization.M, m);
        map.put(Authorization.N, n);
        SqlPara sqlPara = Db.getSqlPara("role.list", map);

        return new Role().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Role find(String role_id) {
        JMap map = JMap.create();
        map.put(Role.ROLE_ID, role_id);
        SqlPara sqlPara = Db.getSqlPara("role.find", map);

        List<Role> roleList = new Role().find(sqlPara.getSql(), sqlPara.getPara());
        if (roleList.size() == 0) {
            return null;
        } else {
            return roleList.get(0);
        }
    }

    public Role save(Role role, String request_user_id) {
        role.setRole_id(Util.getRandomUUID());
        role.setSystem_create_user_id(request_user_id);
        role.setSystem_create_time(new Date());
        role.setSystem_update_user_id(request_user_id);
        role.setSystem_update_time(new Date());
        role.setSystem_status(true);

        role.save();

        return role;
    }

    public boolean update(Role role, String request_user_id) {
        role.remove(Role.SYSTEM_CREATE_USER_ID);
        role.remove(Role.SYSTEM_CREATE_TIME);
        role.setSystem_update_user_id(request_user_id);
        role.setSystem_update_time(new Date());
        role.remove(Role.SYSTEM_STATUS);

        return role.update();
    }

    public boolean delete(String role_id, String request_user_id) {
        JMap map = JMap.create();
        map.put(Role.ROLE_ID, role_id);
        map.put(Role.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(Role.SYSTEM_UPDATE_TIME, new Date());
        map.put(Role.SYSTEM_STATUS, false);
        SqlPara sqlPara = Db.getSqlPara("role.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) == 1;
    }

}