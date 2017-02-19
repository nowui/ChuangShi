package com.shanghaichuangshi.service;

import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.dao.AuthorizationDao;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.List;

public class AuthorizationService extends Service {

    private static final AuthorizationDao authorizationDao = new AuthorizationDao();

    public int count(Authorization authorization) {
        return authorizationDao.count(authorization.getAuthorization_token());
    }

    public List<Authorization> list(Authorization authorization, int m, int n) {
        return authorizationDao.list(authorization.getAuthorization_token(), m, n);
    }

    public Authorization find(Authorization authorization) {
        return authorizationDao.find(authorization.getAuthorization_id());
    }

    public String saveByUser_id(String user_id, String authorization_platform, String authorization_version, String authorization_ip_address, String request_user_id) {
        String authorization_id = Util.getRandomUUID();
        long create_millis = System.currentTimeMillis();
        Date create_time = new Date(create_millis);

        long expMillis = create_millis + 1000 * 60 * 60 * 24 * 360;
        Date expire_time = new Date(expMillis);

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

        authorizationDao.save(authorization, request_user_id);

        return authorization_token;
    }

}