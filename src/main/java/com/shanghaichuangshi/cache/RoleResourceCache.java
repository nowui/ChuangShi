package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.RoleResourceDao;
import com.shanghaichuangshi.model.RoleResource;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.List;

public class RoleResourceCache extends Cache {

    private final String ROLE_RESOURCE_BY_ROLE_ID_CACHE = "role_resource_by_role_id_cache";

    private RoleResourceDao roleResourceDao = new RoleResourceDao();

    public List<RoleResource> list(String role_id) {
        List<RoleResource> roleResourceList = CacheUtil.get(ROLE_RESOURCE_BY_ROLE_ID_CACHE, role_id);

        if (roleResourceList == null) {
            roleResourceList = roleResourceDao.list(role_id);

            CacheUtil.put(ROLE_RESOURCE_BY_ROLE_ID_CACHE, role_id, roleResourceList);
        }

        return roleResourceList;
    }

    public void save(List<RoleResource> roleResourceList, String role_id, String request_user_id) {
        CacheUtil.remove(ROLE_RESOURCE_BY_ROLE_ID_CACHE, role_id);

        roleResourceDao.save(roleResourceList, role_id, request_user_id);
    }

    public void delete(List<String> roleResourceIdList, String role_id, String request_user_id) {
        CacheUtil.remove(ROLE_RESOURCE_BY_ROLE_ID_CACHE, role_id);

        roleResourceDao.delete(roleResourceIdList, role_id, request_user_id);
    }

}