package com.shanghaichuangshi.service;

import com.alibaba.fastjson.JSONArray;
import com.shanghaichuangshi.cache.CategoryCache;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.util.Util;

import java.util.List;
import java.util.Map;

public class CategoryService extends Service {

    private final CategoryCache categoryCache = new CategoryCache();

    public int count(String category_name) {
        return categoryCache.count(category_name);
    }

    public List<Category> list(String category_name, int m, int n) {
        return categoryCache.list(category_name, m, n);
    }

    public List<Map<String, Object>> adminTreeList(String category_id, String... keys) {
        return categoryCache.treeListByCategory_path(category_id, keys);
    }

//    public List<Map<String, Object>> listByCategory_key(String category_key) {
//        return categoryCache.treeListByCategory_key(category_key);
//    }

    public Category find(String category_id) {
        return categoryCache.find(category_id);
    }

    private void checkByCategory_idAndCategory_key(String category_id, String category_key) {
        if (!Util.isNullOrEmpty(category_key)) {
            int count = categoryCache.countByCategory_idAndCategory_key(category_id, category_key);

            if (count > 0) {
                throw new RuntimeException("重复的分类键值：" + category_key);
            }
        }
    }

    public Category save(Category category, String request_user_id) {
        checkByCategory_idAndCategory_key("", category.getCategory_key());

        JSONArray jsonArray = new JSONArray();

        if (Util.isNullOrEmpty(category.getParent_id())) {
            category.setCategory_path(jsonArray.toJSONString());
        } else {
            Category c = categoryCache.find(category.getParent_id());

            if (Util.isNullOrEmpty(c.getCategory_path())) {
                jsonArray.add(c.getCategory_id());

                category.setCategory_path(jsonArray.toJSONString());
            } else {
                jsonArray = JSONArray.parseArray(c.getCategory_path());

                jsonArray.add(c.getCategory_id());

                category.setCategory_path(jsonArray.toJSONString());
            }
        }

        return categoryCache.save(category, request_user_id);
    }

    public boolean update(Category category, String request_user_id) {
        checkByCategory_idAndCategory_key(category.getCategory_id(), category.getCategory_key());

        return categoryCache.update(category, request_user_id);
    }

    public boolean delete(Category category, String request_user_id) {
        return categoryCache.delete(category.getCategory_id(), request_user_id);
    }

}