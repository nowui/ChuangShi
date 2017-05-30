package com.shanghaichuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.util.CacheUtil;
import com.shanghaichuangshi.util.Util;

import java.util.*;

public class CategoryDao extends Dao {

    private final String CATEGORY_BY_CATEGORY_ID_CACHE = "category_by_category_id_cache";

    public int count(String category_name) {
        Kv map = Kv.create();
        map.put(Category.CATEGORY_NAME, category_name);
        SqlPara sqlPara = Db.getSqlPara("category.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public int countByCategory_idAndCategory_key(String category_id, String category_key) {
        Kv map = Kv.create();
        map.put(Category.CATEGORY_ID, category_id);
        map.put(Category.CATEGORY_KEY, category_key);
        SqlPara sqlPara = Db.getSqlPara("category.countByCategory_idAndCategory_key", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Category> list(String category_name, int m, int n) {
        Kv map = Kv.create();
        map.put(Category.CATEGORY_NAME, category_name);
        map.put(Category.M, m);
        map.put(Category.N, n);
        SqlPara sqlPara = Db.getSqlPara("category.list", map);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Map<String, Object>> treeListByCategory_path(String category_id, String... keys) {
        Kv map = Kv.create();
        map.put(Category.CATEGORY_PATH, "%" + category_id + "%");
        SqlPara sqlPara = Db.getSqlPara("category.treeListByCategory_path", map);

        List<Category> categoryList = new Category().find(sqlPara.getSql(), sqlPara.getPara());

        Category category = find(category_id);

        return getChildren(categoryList, category.getCategory_id(), keys);
    }

    public List<Map<String, Object>> treeListByCategory_key(String category_key, String... keys) {
        Kv map = Kv.create();
        map.put(Category.CATEGORY_KEY, category_key);
        SqlPara sqlPara = Db.getSqlPara("category.treeListByCategory_key", map);

        List<Category> categoryList = new Category().find(sqlPara.getSql(), sqlPara.getPara());

        if (categoryList.size() == 0) {
            return new ArrayList<Map<String, Object>>();
        }

        Category category = findByCategory_key(category_key);

        return getChildren(categoryList, category.getCategory_id(), keys);
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

    public Category find(String category_id) {
        Category category = CacheUtil.get(CATEGORY_BY_CATEGORY_ID_CACHE, category_id);

        if (category == null) {
            Kv map = Kv.create();
            map.put(Category.CATEGORY_ID, category_id);
            SqlPara sqlPara = Db.getSqlPara("category.find", map);

            List<Category> categoryList = new Category().find(sqlPara.getSql(), sqlPara.getPara());
            if (categoryList.size() == 0) {
                category = null;
            } else {
                category = categoryList.get(0);

                CacheUtil.put(CATEGORY_BY_CATEGORY_ID_CACHE, category_id, category);
            }
        }

        return category;
    }

    public Category findByCategory_key(String category_key) {
        Kv map = Kv.create();
        map.put(Category.CATEGORY_KEY, category_key);
        SqlPara sqlPara = Db.getSqlPara("category.findByCategory_key", map);

        List<Category> categoryList = new Category().find(sqlPara.getSql(), sqlPara.getPara());
        if (categoryList.size() == 0) {
            return null;
        } else {
            return categoryList.get(0);
        }
    }

    public Category save(Category category, String request_user_id) {
        category.setCategory_id(Util.getRandomUUID());
        category.setSystem_create_user_id(request_user_id);
        category.setSystem_create_time(new Date());
        category.setSystem_update_user_id(request_user_id);
        category.setSystem_update_time(new Date());
        category.setSystem_status(true);
        category.save();

        return category;
    }

    public boolean update(Category category, String request_user_id) {
        CacheUtil.remove(CATEGORY_BY_CATEGORY_ID_CACHE, category.getCategory_id());

        category.remove(Category.SYSTEM_CREATE_USER_ID);
        category.remove(Category.SYSTEM_CREATE_TIME);
        category.setSystem_update_user_id(request_user_id);
        category.setSystem_update_time(new Date());
        category.remove(Category.SYSTEM_STATUS);

        return category.update();
    }

    public boolean delete(String category_id, String request_user_id) {
        CacheUtil.remove(CATEGORY_BY_CATEGORY_ID_CACHE, category_id);

        Kv map = Kv.create();
        map.put(Category.CATEGORY_ID, category_id);
        map.put(Category.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(Category.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("category.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}