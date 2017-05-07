package com.shanghaichuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.model.Log;

import java.util.List;

public class LogDao extends Dao {

    public int count(String log_url) {
        Kv map = Kv.create();
        map.put(Log.LOG_URL, log_url);
        SqlPara sqlPara = Db.getSqlPara("log.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Log> list(String log_url, int m, int n) {
        Kv map = Kv.create();
        map.put(Log.LOG_URL, log_url);
        map.put(Log.M, m);
        map.put(Log.N, n);
        SqlPara sqlPara = Db.getSqlPara("log.list", map);

        return new Log().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Log find(String log_id) {
        Kv map = Kv.create();
        map.put(Log.LOG_ID, log_id);
        SqlPara sqlPara = Db.getSqlPara("log.find", map);

        List<Log> logList = new Log().find(sqlPara.getSql(), sqlPara.getPara());
        if (logList.size() == 0) {
            return null;
        } else {
            return logList.get(0);
        }
    }

}