package com.shanghaichuangshi.service;

import com.shanghaichuangshi.cache.AuthorizationCache;
import com.shanghaichuangshi.model.Authorization;

import java.util.List;

public class AuthorizationService extends Service {

    private final AuthorizationCache authorizationCache = new AuthorizationCache();

    public int count(String authorization_token) {
        return authorizationCache.count(authorization_token);
    }

    public List<Authorization> list(String authorization_token, int m, int n) {
        return authorizationCache.list(authorization_token, m, n);
    }

    public Authorization find(String authorization_id) {
        return authorizationCache.find(authorization_id);
    }

}