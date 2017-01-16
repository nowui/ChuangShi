package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Log;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.List;

public class LogDao extends Dao {

    public int count() {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(Log.TABLE_LOG).append(" ");
        dynamicSQL.append("WHERE ").append(Log.TABLE_LOG).append(".").append(Log.SYSTEM_STATUS).append(" = ? ", true);

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Log> list(String log_url, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Log.TABLE_LOG).append(".").append(Log.LOG_ID).append(", ");
        dynamicSQL.append(Log.TABLE_LOG).append(".").append(Log.LOG_URL).append(", ");
        dynamicSQL.append(Log.TABLE_LOG).append(".").append(Log.LOG_CODE).append(", ");
        dynamicSQL.append(Log.TABLE_LOG).append(".").append(Log.LOG_PLATFORM).append(", ");
        dynamicSQL.append(Log.TABLE_LOG).append(".").append(Log.LOG_VERSION).append(", ");
        dynamicSQL.append(Log.TABLE_LOG).append(".").append(Log.LOG_CREATE_TIME).append(", ");
        dynamicSQL.append(Log.TABLE_LOG).append(".").append(Log.LOG_RUN_TIME).append(" ");
        dynamicSQL.append("FROM ").append(Log.TABLE_LOG).append(" ");
        dynamicSQL.append("WHERE ").append(Log.TABLE_LOG).append(".").append(Log.SYSTEM_STATUS).append(" = ? ", true);
        if (!Util.isNullOrEmpty(log_url)) {
            dynamicSQL.append("AND ").append(Log.TABLE_LOG).append(".").append(Log.LOG_URL).append(" LIKE ? ", "%" + log_url + "%");
        }
        dynamicSQL.append("ORDER BY ").append(Log.TABLE_LOG).append(".").append(Log.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.append("LIMIT ?, ? ", m, n);

        return (List<Log>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Log.class);
    }

    public Log find(String log_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(Log.TABLE_LOG).append(".* ");
        dynamicSQL.append("FROM ").append(Log.TABLE_LOG).append(" ");
        dynamicSQL.append("WHERE ").append(Log.TABLE_LOG).append(".").append(Log.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(Log.TABLE_LOG).append(".").append(Log.LOG_ID).append(" = ? ", log_id);

        return (Log) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), Log.class);
    }

    public boolean save(Log log) {
        log.setLog_id(Util.getRandomUUID());

        return log.save();
    }

    public boolean update(Log log) {
        return log.update();
    }

    public boolean delete(Log log) {
        return log.delete();
    }

}