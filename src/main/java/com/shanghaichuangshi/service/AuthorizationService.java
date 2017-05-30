package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.AuthorizationDao;
import com.shanghaichuangshi.model.Authorization;

import java.util.List;

public class AuthorizationService extends Service {

    private final AuthorizationDao authorizationDao = new AuthorizationDao();

    public int count(String authorization_token) {
        return authorizationDao.count(authorization_token);
    }

    public List<Authorization> list(String authorization_token, int m, int n) {
        return authorizationDao.list(authorization_token, m, n);
    }

    public Authorization find(String authorization_id) {
        return authorizationDao.find(authorization_id);
    }

}