package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.List;

public class CategoryDao extends Dao {

    public int count() {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Category.TABLE_CATEGORY).append(" ");
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_STATUS).append(" = 1 ");

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Category> list(String category_name, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_ID).append(", ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_NAME).append(", ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_KEY).append(" ");
        dynamicSQL.append("FROM ").append(Category.TABLE_CATEGORY).append(" ");
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_STATUS).append(" = 1 ");
        if (!Util.isNullOrEmpty(category_name)) {
            dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_NAME).append(" LIKE ? ", "%" + category_name + "%");
        }
        dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.PARENT_ID).append(" = '' ");
        dynamicSQL.append("ORDER BY ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.append("LIMIT ?, ? ", m, n);

        return (List<Category>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Category.class);
    }

    public Category find(String category_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Category.TABLE_CATEGORY).append(".* ");
        dynamicSQL.append("FROM ").append(Category.TABLE_CATEGORY).append(" ");
        dynamicSQL.append("WHERE ").append(Category.TABLE_CATEGORY).append(".").append(Category.SYSTEM_STATUS).append(" = 1 ");
        dynamicSQL.append("AND ").append(Category.TABLE_CATEGORY).append(".").append(Category.CATEGORY_ID).append(" = ? ", category_id);

        return (Category) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Category.class);
    }

    public boolean save(Category category) {
        return category.save();
    }

    public boolean update(Category category) {
        return category.update();
    }

    public boolean delete(Category category) {
        return category.delete();
    }

}