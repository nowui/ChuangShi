package com.shanghaichuangshi.service;

import com.alibaba.fastjson.JSONArray;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.dao.CategoryDao;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryService extends Service {

    private static final CategoryDao categoryDao = new CategoryDao();

    public int count(Category category) {
        return categoryDao.count(category.getCategory_name());
    }

    public List<Category> list(Category category, int m, int n) {
        return categoryDao.list(category.getCategory_name(), m, n);
    }

    public Category treeList(Category category) {
        Category c = categoryDao.find(category.getCategory_id());

        List<Category> categoryList = categoryDao.treeListByCategory_path(category.getCategory_id());

        c.put(Constant.KEY, c.getCategory_id());
        c.put(Constant.CHILDREN, getChildren(categoryList, c.getCategory_id()));

        return c;
    }

    public Category treeListByCategory_key(String category_key) {
        Category category = categoryDao.findByCategory_key(category_key);

        List<Category> categoryList = categoryDao.treeListByCategory_path(category.getCategory_id());

        category.put(Constant.KEY, category.getCategory_id());
        category.put(Constant.CHILDREN, getMenuChildren(categoryList, category.getCategory_id()));

        return category;
    }

    public List<Category> listByCategory_key(String category_key) {
        List<Category> categoryList = categoryDao.treeListByCategory_key(category_key);

        return categoryList;
    }

    public Category find(String category_id) {
        return categoryDao.find(category_id);
    }

    private void checkByCategory_idAndCategory_key(String category_id, String category_key) {
        if (!Util.isNullOrEmpty(category_key)) {
            int count = categoryDao.countByCategory_idAndCategory_key(category_id, category_key);

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
            Category c = categoryDao.find(category.getParent_id());

            if (Util.isNullOrEmpty(c.getCategory_path())) {
                jsonArray.add(c.getCategory_id());

                category.setCategory_path(jsonArray.toJSONString());
            } else {
                jsonArray = JSONArray.parseArray(c.getCategory_path());

                jsonArray.add(c.getCategory_id());

                category.setCategory_path(jsonArray.toJSONString());
            }
        }

        return categoryDao.save(category, request_user_id);
    }

    public boolean update(Category category, String request_user_id) {
        checkByCategory_idAndCategory_key(category.getCategory_id(), category.getCategory_key());

        return categoryDao.update(category, request_user_id);
    }

    public boolean delete(Category category, String request_user_id) {
        return categoryDao.delete(category.getCategory_id(), request_user_id);
    }

    private List<Map<String, Object>> getChildren(List<Category> categoryList, String parent_id) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Category category : categoryList) {
            if (category.getParent_id().equals(parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(Constant.KEY, category.getCategory_id());
                map.put(Category.CATEGORY_ID, category.getCategory_id());
                map.put(Category.CATEGORY_NAME, category.getCategory_name());
                map.put(Category.CATEGORY_KEY, category.getCategory_key());
                map.put(Category.CATEGORY_SORT, category.getCategory_sort());

                List<Map<String, Object>> childrenList = getChildren(categoryList, category.getCategory_id());
                if (childrenList.size() > 0) {
                    map.put(Constant.CHILDREN, childrenList);
                }
                list.add(map);
            }
        }
        return list;
    }

    private List<Map<String, Object>> getMenuChildren(List<Category> categoryList, String parent_id) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Category category : categoryList) {
            if (category.getParent_id().equals(parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(Constant.KEY, category.getCategory_id());
                map.put(Category.CATEGORY_ID, category.getCategory_id());
                map.put(Category.CATEGORY_NAME, category.getCategory_name());
                map.put(Category.CATEGORY_VALUE, category.getCategory_value());
                map.put(Category.CATEGORY_REMARK, category.getCategory_remark());

                List<Map<String, Object>> childrenList = getMenuChildren(categoryList, category.getCategory_id());
                if (childrenList.size() > 0) {
                    map.put(Constant.CHILDREN, childrenList);
                }
                list.add(map);
            }
        }
        return list;
    }

}