package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.List;

public class AuthorizationDao extends Dao {

    public int count() {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Authorization.TABLE_AUTHORIZATION).append(" ");
        dynamicSQL.append("WHERE ").append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.SYSTEM_STATUS).append(" = ? ", true);

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Authorization> list(String authorization_token, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.AUTHORIZATION_ID).append(", ");
        dynamicSQL.append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.AUTHORIZATION_TOKEN).append(", ");
        dynamicSQL.append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.AUTHORIZATION_PLATFORM).append(", ");
        dynamicSQL.append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.AUTHORIZATION_VERSION).append(", ");
        dynamicSQL.append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.AUTHORIZATION_CREATE_TIME).append(" ");
        dynamicSQL.append("FROM ").append(Authorization.TABLE_AUTHORIZATION).append(" ");
        dynamicSQL.append("WHERE ").append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.SYSTEM_STATUS).append(" = ? ", true);
        if (!Util.isNullOrEmpty(authorization_token)) {
            dynamicSQL.append("AND ").append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.AUTHORIZATION_TOKEN).append(" LIKE ? ", "%" + authorization_token + "%");
        }
        dynamicSQL.append("ORDER BY ").append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.append("LIMIT ?, ? ", m, n);

        return (List<Authorization>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Authorization.class);
    }

    public Authorization find(String authorization_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Authorization.TABLE_AUTHORIZATION).append(".* ");
        dynamicSQL.append("FROM ").append(Authorization.TABLE_AUTHORIZATION).append(" ");
        dynamicSQL.append("WHERE ").append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(Authorization.TABLE_AUTHORIZATION).append(".").append(Authorization.AUTHORIZATION_ID).append(" = ? ", authorization_id);

        return (Authorization) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Authorization.class);
    }

    public boolean save(Authorization authorization) {
        return authorization.save();
    }

    public boolean delete(Authorization authorization) {
        return authorization.delete();
    }

}