package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.AuthorizationDao;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.List;

public class AuthorizationCache extends Cache {

    public static final String AUTHORIZATION_BY_AUTHORIZATION_ID_CACHE = "authorization_by_authorization_id_cache";

    private AuthorizationDao authorizationDao = new AuthorizationDao();

    public int count(String authorization_token) {
        return authorizationDao.count(authorization_token);
    }

    public List<Authorization> list(String authorization_token, int m, int n) {
        return authorizationDao.list(authorization_token, m, n);
    }

    public Authorization find(String authorization_id) {
        Authorization authorization = CacheUtil.get(AUTHORIZATION_BY_AUTHORIZATION_ID_CACHE, authorization_id);

        if (authorization == null) {
            authorization = authorizationDao.find(authorization_id);

            CacheUtil.put(AUTHORIZATION_BY_AUTHORIZATION_ID_CACHE, authorization_id, authorization);
        }

        return authorization;
    }

    public Authorization save(String user_id, String authorization_platform, String authorization_version, String authorization_ip_address, String request_user_id) {
        return authorizationDao.save(user_id, authorization_platform, authorization_version, authorization_ip_address, request_user_id);
    }

    public boolean delete(String authorization_id, String request_user_id) {
        CacheUtil.remove(AUTHORIZATION_BY_AUTHORIZATION_ID_CACHE, authorization_id);

        return authorizationDao.delete(authorization_id, request_user_id);
    }

}