package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Attribute;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.List;

public class AttributeDao extends Dao {

    public int count() {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Attribute.TABLE_ATTRIBUTE).append(" ");
        dynamicSQL.append("WHERE ").append(Attribute.TABLE_ATTRIBUTE).append(".").append(Attribute.SYSTEM_STATUS).append(" = ? ", true);

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Attribute> list(String attribute_name, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Attribute.TABLE_ATTRIBUTE).append(".").append(Attribute.ATTRIBUTE_ID).append(", ");
        dynamicSQL.append(Attribute.TABLE_ATTRIBUTE).append(".").append(Attribute.ATTRIBUTE_NAME).append(" ");
        dynamicSQL.append("FROM ").append(Attribute.TABLE_ATTRIBUTE).append(" ");
        dynamicSQL.append("WHERE ").append(Attribute.TABLE_ATTRIBUTE).append(".").append(Attribute.SYSTEM_STATUS).append(" = ? ", true);
        if (!Util.isNullOrEmpty(attribute_name)) {
            dynamicSQL.append("AND ").append(Attribute.TABLE_ATTRIBUTE).append(".").append(Attribute.ATTRIBUTE_NAME).append(" LIKE ? ", "%" + attribute_name + "%");
        }
        dynamicSQL.append("ORDER BY ").append(Attribute.TABLE_ATTRIBUTE).append(".").append(Attribute.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.append("LIMIT ?, ? ", m, n);

        return (List<Attribute>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Attribute.class);
    }

    public Attribute find(String attribute_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Attribute.TABLE_ATTRIBUTE).append(".* ");
        dynamicSQL.append("FROM ").append(Attribute.TABLE_ATTRIBUTE).append(" ");
        dynamicSQL.append("WHERE ").append(Attribute.TABLE_ATTRIBUTE).append(".").append(Attribute.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(Attribute.TABLE_ATTRIBUTE).append(".").append(Attribute.ATTRIBUTE_ID).append(" = ? ", attribute_id);

        return (Attribute) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Attribute.class);
    }

    public boolean save(Attribute attribute) {
        attribute.setAttribute_id(Util.getRandomUUID());

        return attribute.save();
    }

    public boolean update(Attribute attribute) {
        return attribute.update();
    }

    public boolean delete(Attribute attribute) {
        return attribute.delete();
    }

}