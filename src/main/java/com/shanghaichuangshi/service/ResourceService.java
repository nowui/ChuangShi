package com.shanghaichuangshi.service;

import com.shanghaichuangshi.cache.CategoryCache;
import com.shanghaichuangshi.cache.ResourceCache;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.type.CategoryTypeEnum;

import java.util.List;
import java.util.Map;

public class ResourceService extends Service {

    private final ResourceCache resourceCache = new ResourceCache();
    private final CategoryCache categoryCache = new CategoryCache();

    public int count(String category_id, String resource_name) {
        return resourceCache.count(category_id, resource_name);
    }

    public List<Resource> list(String category_id, String resource_name, int m, int n) {
        List<Resource> resourceList = resourceCache.list(category_id, resource_name, m, n);

        for (Resource resource : resourceList) {
            Category category = categoryCache.find(resource.getCategory_id());

            resource.put(Category.CATEGORY_NAME, category.getCategory_name());
        }

        return resourceList;
    }

    public List<Resource> allList() {
        return resourceCache.allList();
    }

    public List<Map<String, Object>> categoryList() {
        return categoryCache.treeListByCategory_key(CategoryTypeEnum.RESOURCE.getKey());
    }

    public Resource find(String resource_id) {
        return resourceCache.find(resource_id);
    }

    public Resource save(Resource resource, String request_user_id) {
        return resourceCache.save(resource, request_user_id);
    }

    public boolean update(Resource resource, String request_user_id) {
        return resourceCache.update(resource, request_user_id);
    }

    public boolean delete(Resource resource, String request_user_id) {
        return resourceCache.delete(resource.getResource_id(), request_user_id);
    }

}