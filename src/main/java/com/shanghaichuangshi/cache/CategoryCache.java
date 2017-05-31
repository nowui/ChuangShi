package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.CategoryDao;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.*;

public class CategoryCache extends Cache {

    private final String CATEGORY_BY_CATEGORY_ID_CACHE = "category_by_category_id_cache";

    private CategoryDao categoryDao = new CategoryDao();

    public int count(String category_name) {
        return categoryDao.count(category_name);
    }

    public int countByCategory_idAndCategory_key(String category_id, String category_key) {
        return categoryDao.countByCategory_idAndCategory_key(category_id, category_key);
    }

    public List<Category> list(String category_name, int m, int n) {
        return categoryDao.list(category_name, m, n);
    }

    public List<Map<String, Object>> treeListByCategory_path(String category_id, String... keys) {
        return categoryDao.treeListByCategory_path(category_id, keys);
    }

    public List<Map<String, Object>> treeListByCategory_key(String category_key, String... keys) {
        return categoryDao.treeListByCategory_key(category_key, keys);
    }

    public Category find(String category_id) {
        Category category = CacheUtil.get(CATEGORY_BY_CATEGORY_ID_CACHE, category_id);

        if (category == null) {
            category = categoryDao.find(category_id);

            CacheUtil.put(CATEGORY_BY_CATEGORY_ID_CACHE, category_id, category);
        }

        return category;
    }

    public Category findByCategory_key(String category_key) {
        return categoryDao.findByCategory_key(category_key);
    }

    public Category save(Category category, String request_user_id) {
        return categoryDao.save(category, request_user_id);
    }

    public boolean update(Category category, String request_user_id) {
        CacheUtil.remove(CATEGORY_BY_CATEGORY_ID_CACHE, category.getCategory_id());

        return categoryDao.update(category, request_user_id);
    }

    public boolean delete(String category_id, String request_user_id) {
        CacheUtil.remove(CATEGORY_BY_CATEGORY_ID_CACHE, category_id);

        return categoryDao.delete(category_id, request_user_id);
    }

}