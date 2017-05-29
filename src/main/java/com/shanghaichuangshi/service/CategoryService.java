package com.shanghaichuangshi.service;

import com.alibaba.fastjson.JSONArray;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.dao.CategoryDao;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.type.CategoryType;
import com.shanghaichuangshi.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryService extends Service {

    private final CategoryDao categoryDao = new CategoryDao();

    public int count(String category_name) {
        return categoryDao.count(category_name);
    }

    public List<Category> list(String category_name, int m, int n) {
        return categoryDao.list(category_name, m, n);
    }

    public Category treeList(String category_id, String... keys) {
        Category category = categoryDao.find(category_id);

        List<Category> categoryList = categoryDao.treeListByCategory_path(category_id);

        Category result = new Category();
        result.put(Category.CATEGORY_ID, category.getCategory_id());
        result.put(Category.CATEGORY_NAME, category.getCategory_name());
        result.put(Constant.CHILDREN, getChildren(categoryList, category.getCategory_id(), keys));

        return result;
    }

    public Category treeListByCategory_key(String category_key, String... keys) {
        Category category = categoryDao.findByCategory_key(category_key);

        List<Category> categoryList = categoryDao.treeListByCategory_path(category.getCategory_id());

        Category result = new Category();
        result.put(Category.CATEGORY_ID, category.getCategory_id());
        result.put(Category.CATEGORY_NAME, category.getCategory_name());
        result.put(Constant.CHILDREN, getChildren(categoryList, category.getCategory_id(), keys));

        return result;
    }

//    public List<Map<String, Object>> treeChinaList() {
//        Category category = categoryDao.findByCategory_key(CategoryType.CHINA.getKey());
//
//        List<Category> categoryList = categoryDao.treeListByCategory_path(category.getCategory_id());
//
//        List<Map<String, Object>> resultList = getChinaChildren(categoryList, category.getCategory_id());
//
//        return resultList;
//    }

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

    private List<Map<String, Object>> getChildren(List<Category> categoryList, String parent_id, String... keys) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Category category : categoryList) {
            if (category.getParent_id().equals(parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(Category.CATEGORY_ID, category.getCategory_id());
                map.put(Category.CATEGORY_NAME, category.getCategory_name());

                for (String key : keys) {
                    map.put(key, category.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(categoryList, category.getCategory_id(), keys);
                if (childrenList.size() > 0) {
                    map.put(Constant.CHILDREN, childrenList);
                }
                list.add(map);
            }
        }
        return list;
    }

//    private List<Map<String, Object>> getChinaChildren(List<Category> categoryList, String parent_id) {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        for (Category category : categoryList) {
//            if (category.getParent_id().equals(parent_id)) {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("value", category.getCategory_id());
//                map.put("label", category.getCategory_name());
//
//                List<Map<String, Object>> childrenList = getChinaChildren(categoryList, category.getCategory_id());
//                if (childrenList.size() > 0) {
//                    map.put(Constant.CHILDREN, childrenList);
//                }
//                list.add(map);
//            }
//        }
//        return list;
//    }

}