package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.ResourceDao;
import com.shanghaichuangshi.model.Resource;

import java.util.List;

public class ResourceService extends Service {

    private static final ResourceDao resourceDao = new ResourceDao();

    public int count(Resource resource) {
        return resourceDao.count(resource.getResource_name());
    }

    public List<Resource> list(Resource resource, int m, int n) {
        return resourceDao.list(resource.getResource_name(), m, n);
    }

    public Resource find(String resource_id) {
        return resourceDao.find(resource_id);
    }

    public Resource save(Resource resource, String request_user_id) {
        return resourceDao.save(resource, request_user_id);
    }

    public boolean update(Resource resource, String request_user_id) {
        return resourceDao.update(resource, request_user_id);
    }

    public boolean delete(Resource resource, String request_user_id) {
        return resourceDao.delete(resource.getResource_id(), request_user_id);
    }

}