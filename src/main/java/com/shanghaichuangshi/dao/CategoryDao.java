package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class CategoryDao extends Dao {

    public int count() {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Category.TABLE_CATEGORY).append(" ");
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_STATUS).append(" = ? ", true);

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public int countByCategory_idAndCategory_key(String category_id, String category_key) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Category.TABLE_CATEGORY).append(" ");
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_STATUS).append(" = ? ", true);
        if (!Util.isNullOrEmpty(category_id)) {
            dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_ID).append(" != ? ", category_id);
        }
        dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_KEY).append(" = ? ", category_key);

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Category> list(String category_name, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_ID).append(", ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_NAME).append(", ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_KEY).append(", ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_SORT).append(" ");
        dynamicSQL.append("FROM ").append(Category.TABLE_CATEGORY).append(" ");
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_STATUS).append(" = ? ", true);
        if (!Util.isNullOrEmpty(category_name)) {
            dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_NAME).append(" LIKE ? ", "%" + category_name + "%");
        }
        dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.PARENT_ID).append(" = '' ");
        dynamicSQL.append("ORDER BY ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_SORT).append(" ASC, ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.append("LIMIT ?, ? ", m, n);

        return (List<Category>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Category.class);
    }

    public List<Category> listByCategory_path(String category_path) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_ID).append(", ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.PARENT_ID).append(", ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_NAME).append(", ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_KEY).append(", ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_SORT).append(" ");
        dynamicSQL.append("FROM ").append(Category.TABLE_CATEGORY).append(" ");
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_PATH).append(" LIKE ? ", "%\"" + category_path + "\"%");
        dynamicSQL.append("ORDER BY ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_SORT).append(" ASC, ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_CREATE_TIME).append(" DESC ");

        return (List<Category>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Category.class);
    }

    public Category find(String category_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".* ");
        dynamicSQL.append("FROM ").append(Category.TABLE_CATEGORY).append(" ");
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_ID).append(" = ? ", category_id);

        return (Category) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Category.class);
    }

    public Category findByCategory_key(String category_key) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".* ");
        dynamicSQL.append("FROM ").append(Category.TABLE_CATEGORY).append(" ");
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_KEY).append(" = ? ", category_key);

        return (Category) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Category.class);
    }

    public boolean save(Category category) {
        category.setCategory_id(Util.getRandomUUID());

        return category.save();
    }

    public boolean update(Category category) {
        return category.update();
    }

    public boolean delete(String category_id, String request_user_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("UPDATE ").append(Category.TABLE_CATEGORY).append(" SET ");
        dynamicSQL.append(Category.SYSTEM_UPDATE_USER_ID).append(" = ?, ", request_user_id);
        dynamicSQL.append(Category.SYSTEM_UPDATE_TIME).append(" = ?, ", new Date());
        dynamicSQL.append(Category.SYSTEM_STATUS).append(" = ? ", false);
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_ID).append(" = ? ", category_id);
        dynamicSQL.append("OR ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_PATH).append(" LIKE ? ", "%\"" + category_id + "\"%");

        return DatabaseUtil.update(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

}