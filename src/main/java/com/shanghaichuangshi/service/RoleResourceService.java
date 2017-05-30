package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.RoleResourceDao;
import com.shanghaichuangshi.model.RoleResource;

import java.util.List;

public class RoleResourceService extends Service {

    private final RoleResourceDao roleResourceDao = new RoleResourceDao();

    public List<RoleResource> list(String role_id) {
        return roleResourceDao.list(role_id);
    }

    public void save(List<RoleResource> roleResourceList, String role_id, String request_user_id) {
        if (roleResourceList.size() > 0) {
            roleResourceDao.save(roleResourceList, role_id, request_user_id);
        }
    }

    public void delete(List<String> roleResourceIdList, String role_id, String request_user_id) {
        roleResourceDao.delete(roleResourceIdList, role_id, request_user_id);
    }

}