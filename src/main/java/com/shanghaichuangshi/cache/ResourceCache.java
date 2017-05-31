package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.ResourceDao;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.List;

public class ResourceCache extends Cache {

    private final String RESOURCE_BY_RESOURCE_ID_CACHE = "resource_by_resource_id_cache";

    private ResourceDao resourceDao = new ResourceDao();

    public int count(String category_id, String resource_name) {
        return resourceDao.count(category_id, resource_name);
    }

    public List<Resource> list(String category_id, String resource_name, Integer m, Integer n) {
        return resourceDao.list(category_id, resource_name, m, n);
    }

    public List<Resource> allList() {
        return resourceDao.allList();
    }

    public Resource find(String resource_id) {
        Resource resource = CacheUtil.get(RESOURCE_BY_RESOURCE_ID_CACHE, resource_id);

        if (resource == null) {
            resource = resourceDao.find(resource_id);

            CacheUtil.put(RESOURCE_BY_RESOURCE_ID_CACHE, resource_id, resource);
        }

        return resource;
    }

    public Resource save(Resource resource, String request_user_id) {
        return resourceDao.save(resource, request_user_id);
    }

    public boolean update(Resource resource, String request_user_id) {
        CacheUtil.remove(RESOURCE_BY_RESOURCE_ID_CACHE, resource.getResource_id());

        return resourceDao.update(resource, request_user_id);
    }

    public boolean delete(String resource_id, String request_user_id) {
        CacheUtil.remove(RESOURCE_BY_RESOURCE_ID_CACHE, resource_id);

        return resourceDao.delete(resource_id, request_user_id);
    }

}