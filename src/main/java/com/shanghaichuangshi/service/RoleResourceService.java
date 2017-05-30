package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.RoleResourceDao;
import com.shanghaichuangshi.model.RoleResource;

import java.util.List;

public class RoleResourceService extends Service {

    private final RoleResourceDao roleResourceDao = new RoleResourceDao();

    public List<RoleResource> list(String role_id) {
        return roleResourceDao.list(role_id);
    }

}