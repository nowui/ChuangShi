package com.shanghaichuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AuthorizationDao extends Dao {

    public int count(String authorization_token) {
        Kv map = Kv.create();
        map.put(Authorization.AUTHORIZATION_TOKEN, authorization_token);
        SqlPara sqlPara = Db.getSqlPara("authorization.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Authorization> list(String authorization_token, int m, int n) {
        Kv map = Kv.create();
        map.put(Authorization.AUTHORIZATION_TOKEN, authorization_token);
        map.put(Authorization.M, m);
        map.put(Authorization.N, n);
        SqlPara sqlPara = Db.getSqlPara("authorization.list", map);

        return new Authorization().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Authorization find(String authorization_id) {
        Kv map = Kv.create();
        map.put(Authorization.AUTHORIZATION_ID, authorization_id);
        SqlPara sqlPara = Db.getSqlPara("authorization.find", map);

        List<Authorization> authorizationList = new Authorization().find(sqlPara.getSql(), sqlPara.getPara());
        if (authorizationList.size() == 0) {
            return null;
        } else {
            return authorizationList.get(0);
        }
    }

    public Authorization save(String user_id, String authorization_platform, String authorization_version, String authorization_ip_address, String request_user_id) {
        String authorization_id = Util.getRandomUUID();
        Date create_time = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(create_time);
        calendar.add(Calendar.YEAR, 100);
        Date expire_time = calendar.getTime();

        Key key = new SecretKeySpec(Constant.PRIVATE_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        String authorization_token = Jwts.builder().setIssuedAt(create_time).setExpiration(expire_time).claim(Authorization.AUTHORIZATION_ID, authorization_id).claim(User.USER_ID, user_id).signWith(SignatureAlgorithm.HS512, key).compact();

        Authorization authorization = new Authorization();
        authorization.setAuthorization_id(authorization_id);
        authorization.setAuthorization_token(authorization_token);
        authorization.setUser_id(user_id);
        authorization.setAuthorization_platform(authorization_platform);
        authorization.setAuthorization_version(authorization_version);
        authorization.setAuthorization_ip_address(authorization_ip_address);
        authorization.setAuthorization_create_time(create_time);
        authorization.setAuthorization_expire_time(expire_time);
        authorization.setSystem_create_user_id(request_user_id);
        authorization.setSystem_create_time(new Date());
        authorization.setSystem_update_user_id(request_user_id);
        authorization.setSystem_update_time(new Date());
        authorization.setSystem_status(true);
        authorization.save();

        return authorization;
    }

    public boolean delete(String authorization_id, String request_user_id) {
        Kv map = Kv.create();
        map.put(Authorization.AUTHORIZATION_ID, authorization_id);
        map.put(Authorization.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(Authorization.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("authorization.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}