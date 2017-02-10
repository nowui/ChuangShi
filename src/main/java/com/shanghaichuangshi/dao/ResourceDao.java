package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.List;

public class ResourceDao extends Dao {

    public int count() {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Resource.TABLE_RESOURCE).append(" ");
        dynamicSQL.append("WHERE ").append(Resource.TABLE_RESOURCE).append(".").append(Resource.SYSTEM_STATUS).append(" = ? ", true);

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Resource> list(String resource_name, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Resource.TABLE_RESOURCE).append(".").append(Resource.RESOURCE_ID).append(", ");
        dynamicSQL.append(Resource.TABLE_RESOURCE).append(".").append(Resource.RESOURCE_NAME).append(" ");
        dynamicSQL.append("FROM ").append(Resource.TABLE_RESOURCE).append(" ");
        dynamicSQL.append("WHERE ").append(Resource.TABLE_RESOURCE).append(".").append(Resource.SYSTEM_STATUS).append(" = ? ", true);
        if (!Util.isNullOrEmpty(resource_name)) {
            dynamicSQL.append("AND ").append(Resource.TABLE_RESOURCE).append(".").append(Resource.RESOURCE_NAME).append(" LIKE ? ", "%" + resource_name + "%");
        }
        dynamicSQL.append("ORDER BY ").append(Resource.TABLE_RESOURCE).append(".").append(Resource.SYSTEM_CREATE_TIME).append(" DESC ");
        if (n > 0) {
            dynamicSQL.append("LIMIT ?, ? ", m, n);
        }

        return (List<Resource>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Resource.class);
    }

    public Resource find(String resource_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Resource.TABLE_RESOURCE).append(".* ");
        dynamicSQL.append("FROM ").append(Resource.TABLE_RESOURCE).append(" ");
        dynamicSQL.append("WHERE ").append(Resource.TABLE_RESOURCE).append(".").append(Resource.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(Resource.TABLE_RESOURCE).append(".").append(Resource.RESOURCE_ID).append(" = ? ", resource_id);

        return (Resource) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Resource.class);
    }

    public boolean save(Resource resource) {
        resource.setResource_id(Util.getRandomUUID());

        return resource.save();
    }

    public boolean update(Resource resource) {
        return resource.update();
    }

    public boolean delete(Resource resource) {
        return resource.delete();
    }

}