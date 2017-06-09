package com.shanghaichuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.model.Count;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class CountDao extends Dao {

    public Count find(String count_type, String object_id, String count_key) {
        Kv map = Kv.create();
        map.put(Count.COUNT_TYPE, count_type);
        map.put(Count.OBJECT_ID, object_id);
        map.put(Count.COUNT_KEY, count_key);
        SqlPara sqlPara = Db.getSqlPara("count.find", map);

        List<Count> countList = new Count().find(sqlPara.getSql(), sqlPara.getPara());
        if (countList.size() == 0) {
            return null;
        } else {
            return countList.get(0);
        }
    }

    public Count save(Count count, String request_user_id) {
        count.setCount_id(Util.getRandomUUID());
        count.setSystem_create_user_id(request_user_id);
        count.setSystem_create_time(new Date());
        count.setSystem_update_user_id(request_user_id);
        count.setSystem_update_time(new Date());
        count.setSystem_status(true);

        count.save();

        return count;
    }

    public boolean update(Count count, String request_user_id) {
        count.remove(Count.SYSTEM_CREATE_USER_ID);
        count.remove(Count.SYSTEM_CREATE_TIME);
        count.setSystem_update_user_id(request_user_id);
        count.setSystem_update_time(new Date());
        count.remove(Count.SYSTEM_STATUS);

        return count.update();
    }

    public boolean delete(String count_id, String request_user_id) {
        Kv map = Kv.create();
        map.put(Count.COUNT_ID, count_id);
        map.put(Count.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(Count.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("count.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}