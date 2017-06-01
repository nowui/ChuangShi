package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.RoleResourceDao;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.model.RoleResource;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.ArrayList;
import java.util.List;

public class RoleResourceCache extends Cache {

    private final String ROLE_RESOURCE_LIST_BY_ROLE_ID_CACHE = "role_resource_list_by_role_id_cache";
    private final String ROLE_RESOURCE_LIST_BY_ROLE_KEY_CACHE = "role_resource_list_by_role_key_cache";

    private RoleResourceDao roleResourceDao = new RoleResourceDao();

    public List<RoleResource> list(String role_id) {
        List<RoleResource> roleResourceList = CacheUtil.get(ROLE_RESOURCE_LIST_BY_ROLE_ID_CACHE, role_id);

        if (roleResourceList == null) {
            roleResourceList = roleResourceDao.list(role_id);

            CacheUtil.put(ROLE_RESOURCE_LIST_BY_ROLE_ID_CACHE, role_id, roleResourceList);
        }

        return roleResourceList;
    }

    public List<String> listByRole_key(String role_key) {
        List<RoleResource> roleResourceList = CacheUtil.get(ROLE_RESOURCE_LIST_BY_ROLE_KEY_CACHE, role_key);

        if (roleResourceList == null) {
            roleResourceList = roleResourceDao.listByRole_key(role_key);

            CacheUtil.put(ROLE_RESOURCE_LIST_BY_ROLE_KEY_CACHE, role_key, roleResourceList);
        }

        List<String> resourceValueList = new ArrayList<String>();
        for (RoleResource roleResource : roleResourceList) {
            resourceValueList.add(roleResource.getResource_value());
        }

        return resourceValueList;
    }

    public void save(List<RoleResource> roleResourceList, String role_id, String role_key, String request_user_id) {
        CacheUtil.remove(ROLE_RESOURCE_LIST_BY_ROLE_ID_CACHE, role_id);
        CacheUtil.remove(ROLE_RESOURCE_LIST_BY_ROLE_KEY_CACHE, role_key);

        roleResourceDao.save(roleResourceList, role_id, request_user_id);
    }

    public void delete(List<String> roleResourceIdList, String role_id, String role_key, String request_user_id) {
        CacheUtil.remove(ROLE_RESOURCE_LIST_BY_ROLE_ID_CACHE, role_id);
        CacheUtil.remove(ROLE_RESOURCE_LIST_BY_ROLE_KEY_CACHE, role_key);

        roleResourceDao.delete(roleResourceIdList, role_id, request_user_id);
    }

}