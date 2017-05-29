package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.ResourceDao;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.type.CategoryType;

import java.util.List;

public class ResourceService extends Service {

    private final ResourceDao resourceDao = new ResourceDao();

    private final CategoryService categoryService = new CategoryService();

    public int count(String resource_name) {
        return resourceDao.count(resource_name);
    }

    public List<Resource> list(String resource_name, int m, int n) {
        return resourceDao.list(resource_name, m, n);
    }

    public Category categoryList() {
        return categoryService.treeListByCategory_key(CategoryType.RESOURCE.getKey());
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