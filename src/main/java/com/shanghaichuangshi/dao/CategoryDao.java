package com.shanghaichuangshi.dao;

import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class CategoryDao extends Dao {

    public int count(String category_name) {
        JMap map = JMap.create();
        map.put(Category.CATEGORY_NAME, category_name);
        SqlPara sqlPara = Db.getSqlPara("category.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public int countByCategory_idAndCategory_key(String category_id, String category_key) {
        JMap map = JMap.create();
        map.put(Category.CATEGORY_ID, category_id);
        map.put(Category.CATEGORY_KEY, category_key);
        SqlPara sqlPara = Db.getSqlPara("category.countByCategory_idAndCategory_key", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Category> list(String category_name, int m, int n) {
        JMap map = JMap.create();
        map.put(Category.CATEGORY_NAME, category_name);
        map.put(Category.M, m);
        map.put(Category.N, n);
        SqlPara sqlPara = Db.getSqlPara("category.list", map);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Category> treeListByCategory_path(String category_path) {
        JMap map = JMap.create();
        map.put(Category.CATEGORY_PATH, "%" + category_path + "%");
        SqlPara sqlPara = Db.getSqlPara("category.treeListByCategory_path", map);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Category> treeListByCategory_key(String category_key) {
        JMap map = JMap.create();
        map.put(Category.CATEGORY_KEY, category_key);
        SqlPara sqlPara = Db.getSqlPara("category.treeListByCategory_key", map);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Category find(String category_id) {
        JMap map = JMap.create();
        map.put(Category.CATEGORY_ID, category_id);
        SqlPara sqlPara = Db.getSqlPara("category.find", map);

        List<Category> categoryList = new Category().find(sqlPara.getSql(), sqlPara.getPara());
        if (categoryList.size() == 0) {
            return null;
        } else {
            return categoryList.get(0);
        }
    }

    public Category findByCategory_key(String category_key) {
        JMap map = JMap.create();
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
        category.remove(Category.SYSTEM_CREATE_USER_ID);
        category.remove(Category.SYSTEM_CREATE_TIME);
        category.setSystem_update_user_id(request_user_id);
        category.setSystem_update_time(new Date());
        category.remove(Category.SYSTEM_STATUS);

        return category.update();
    }

    public boolean delete(String category_id, String request_user_id) {
        JMap map = JMap.create();
        map.put(Category.CATEGORY_ID, category_id);
        map.put(Category.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(Category.SYSTEM_UPDATE_TIME, new Date());
        map.put(Category.SYSTEM_STATUS, false);
        SqlPara sqlPara = Db.getSqlPara("category.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) == 1;
    }

}