package com.shanghaichuangshi.service;

import com.alibaba.fastjson.JSONArray;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.cache.CategoryCache;
import com.shanghaichuangshi.cache.ResourceCache;
import com.shanghaichuangshi.cache.RoleCache;
import com.shanghaichuangshi.cache.RoleResourceCache;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.model.RoleResource;
import com.shanghaichuangshi.type.CategoryTypeEnum;
import com.shanghaichuangshi.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleService extends Service {

    private final RoleCache roleCache = new RoleCache();
    private final CategoryCache categoryCache = new CategoryCache();
    private final ResourceCache resourceCache = new ResourceCache();
    private final RoleResourceCache roleResourceCache = new RoleResourceCache();

    public int count(String role_name) {
        return roleCache.count(role_name);
    }

    public List<Role> list(String role_name, int m, int n) {
        List<Role> roleList = roleCache.list(role_name, m, n);

        for (Role role : roleList) {
            Category category = categoryCache.find(role.getCategory_id());

            role.put(Category.CATEGORY_NAME, category.getCategory_name());
        }

        return roleList;
    }

    public List<Map<String, Object>> categoryList() {
        return categoryCache.treeListByCategory_key(CategoryTypeEnum.ROLE.getKey());
    }

    public Role find(String role_id) {
        return roleCache.find(role_id);
    }

    public Role findByRole_key(String role_key) {
        return roleCache.findByRole_key(role_key);
    }

    public List<Map<String, Object>> resourceFind(String role_id) {
        List<Map<String, Object>> resultList = categoryCache.treeListByCategory_key(CategoryTypeEnum.RESOURCE.getKey());

        List<Resource> resourceList = resourceCache.allList();

        List<RoleResource> roleResourceList = roleResourceCache.list(role_id);

        for (Resource resource : resourceList) {
            resource.put("is_check", false);

            for (RoleResource roleResource : roleResourceList) {
                if (resource.getResource_id().equals(roleResource.getResource_id())) {
                    resource.put("is_check", true);

                    break;
                }
            }
        }

        check(resultList, resourceList);

        return resultList;
    }

    private void check(List<Map<String, Object>> resultList, List<Resource> resourceList) {
        for (Map<String, Object> resultMap : resultList) {
            if (Util.isNullOrEmpty(resultMap.get(Constant.CHILDREN))) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

                for (Resource resource : resourceList) {
                    if (resultMap.get(Category.CATEGORY_ID).equals(resource.getCategory_id())) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("is_leaf", true);
                        map.put("is_check", resource.get("is_check"));
                        map.put(Category.CATEGORY_ID, resource.getResource_id());
                        map.put(Category.CATEGORY_NAME, resource.getResource_name() + " - " + resource.getResource_value());

                        list.add(map);
                    }
                }

                if (list.size() > 0) {
                    resultMap.put(Constant.CHILDREN, list);
                }
            } else {
                resultMap.put("is_leaf", false);
                resultMap.put("is_check", false);

                check((List<Map<String, Object>>) resultMap.get(Constant.CHILDREN), resourceList);
            }
        }
    }

    public Role save(Role role, String request_user_id) {
        return roleCache.save(role, request_user_id);
    }

    public void resourceSave(String role_id, JSONArray jsonArray, String request_user_id) {
        List<RoleResource> roleResourceList = roleResourceCache.list(role_id);
        List<RoleResource> roleResourceSaveList = new ArrayList<RoleResource>();
        List<String> roleResourceDeleteList = new ArrayList<String>();

        for (int i = 0; i < jsonArray.size(); i++) {
            String resource_id = jsonArray.getString(i);

            Boolean isSave = true;

            for (RoleResource roleResource : roleResourceList) {
                if (roleResource.getResource_id().equals(resource_id)) {
                    isSave = false;

                    break;
                }
            }

            if (isSave) {
                RoleResource roleResource = new RoleResource();
                roleResource.setRole_id(role_id);
                roleResource.setResource_id(resource_id);

                roleResourceSaveList.add(roleResource);
            }
        }

        for (RoleResource roleResource : roleResourceList) {
            Boolean isDelete = true;

            for (int i = 0; i < jsonArray.size(); i++) {
                String resource_id = jsonArray.getString(i);

                if (roleResource.getResource_id().equals(resource_id)) {
                    isDelete = false;

                    break;
                }
            }

            if (isDelete) {
                roleResourceDeleteList.add(roleResource.getRole_resource_id());
            }
        }

        Role role = roleCache.find(role_id);

        roleResourceCache.delete(roleResourceDeleteList, role_id, role.getRole_key(), request_user_id);
        roleResourceCache.save(roleResourceSaveList, role_id, role.getRole_key(), request_user_id);
    }

    public boolean update(Role role, String request_user_id) {
        return roleCache.update(role, request_user_id);
    }

    public boolean delete(Role role, String request_user_id) {
        return roleCache.delete(role.getRole_id(), request_user_id);
    }

}