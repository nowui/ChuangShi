package com.shanghaichuangshi.service;

import com.shanghaichuangshi.cache.RoleResourceCache;
import com.shanghaichuangshi.model.RoleResource;

import java.util.List;

public class RoleResourceService extends Service {

    private final RoleResourceCache roleResourceCache = new RoleResourceCache();

    public List<RoleResource> list(String role_id) {
        return roleResourceCache.list(role_id);
    }

    public List<String> listByRole_key(String role_key) {
        return roleResourceCache.listByRole_key(role_key);
    }

}