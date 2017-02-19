package com.shanghaichuangshi.dao;

import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.model.Authorization;

import java.util.Date;
import java.util.List;

public class AuthorizationDao extends Dao {

    public int count(String authorization_token) {
        JMap map = JMap.create();
        map.put(Authorization.AUTHORIZATION_TOKEN, authorization_token);
        SqlPara sqlPara = Db.getSqlPara("authorization.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Authorization> list(String authorization_token, int m, int n) {
        JMap map = JMap.create();
        map.put(Authorization.AUTHORIZATION_TOKEN, authorization_token);
        map.put(Authorization.M, m);
        map.put(Authorization.N, n);
        SqlPara sqlPara = Db.getSqlPara("authorization.list", map);

        return new Authorization().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Authorization find(String authorization_id) {
        JMap map = JMap.create();
        map.put(Authorization.AUTHORIZATION_ID, authorization_id);
        SqlPara sqlPara = Db.getSqlPara("authorization.find", map);

        List<Authorization> authorizationList = new Authorization().find(sqlPara.getSql(), sqlPara.getPara());
        if (authorizationList.size() == 0) {
            return null;
        } else {
            return authorizationList.get(0);
        }
    }

    public Authorization save(Authorization authorization, String request_user_id) {
        authorization.setSystem_create_user_id(request_user_id);
        authorization.setSystem_create_time(new Date());
        authorization.setSystem_update_user_id(request_user_id);
        authorization.setSystem_update_time(new Date());
        authorization.setSystem_status(true);
        authorization.save();

        return authorization;
    }

}