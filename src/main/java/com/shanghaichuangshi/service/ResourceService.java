package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.ResourceDao;
import com.shanghaichuangshi.model.Resource;

import java.util.List;

public class ResourceService extends Service {

    private final ResourceDao resourceDao = new ResourceDao();

    public int count(Resource resource) {
        return resourceDao.count();
    }

    public List<Resource> list(Resource resource) {
        return resourceDao.list(resource.getResource_name(), resource.getM(), resource.getN());
    }

    public Resource find(Resource resource) {
        return resourceDao.find(resource.getResource_id());
    }

    public void save(Resource resource) {
        resourceDao.save(resource);
    }

    public void update(Resource resource) {
        resourceDao.update(resource);
    }

    public void delete(Resource resource) {
        resourceDao.delete(resource);
    }

}