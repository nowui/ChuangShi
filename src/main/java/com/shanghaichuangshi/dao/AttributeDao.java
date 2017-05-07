package com.shanghaichuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.model.Attribute;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class AttributeDao extends Dao {

    public int count(String attribute_name) {
        Kv map = Kv.create();
        map.put(Attribute.ATTRIBUTE_NAME, attribute_name);
        SqlPara sqlPara = Db.getSqlPara("attribute.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Attribute> list(String attribute_name, Integer m, Integer n) {
        Kv map = Kv.create();
        map.put(Attribute.ATTRIBUTE_NAME, attribute_name);
        map.put(Attribute.M, m);
        map.put(Attribute.N, n);
        SqlPara sqlPara = Db.getSqlPara("attribute.list", map);

        return new Attribute().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Attribute find(String attribute_id) {
        Kv map = Kv.create();
        map.put(Attribute.ATTRIBUTE_ID, attribute_id);
        SqlPara sqlPara = Db.getSqlPara("attribute.find", map);

        List<Attribute> attributeList = new Attribute().find(sqlPara.getSql(), sqlPara.getPara());
        if (attributeList.size() == 0) {
            return null;
        } else {
            return attributeList.get(0);
        }
    }

    public Attribute save(Attribute attribute, String request_user_id) {
        attribute.setAttribute_id(Util.getRandomUUID());
        attribute.setSystem_create_user_id(request_user_id);
        attribute.setSystem_create_time(new Date());
        attribute.setSystem_update_user_id(request_user_id);
        attribute.setSystem_update_time(new Date());
        attribute.setSystem_status(true);

        attribute.save();

        return attribute;
    }

    public boolean update(Attribute attribute, String request_user_id) {
        attribute.remove(Attribute.SYSTEM_CREATE_USER_ID);
        attribute.remove(Attribute.SYSTEM_CREATE_TIME);
        attribute.setSystem_update_user_id(request_user_id);
        attribute.setSystem_update_time(new Date());
        attribute.remove(Attribute.SYSTEM_STATUS);

        return attribute.update();
    }

    public boolean delete(String attribute_id, String request_user_id) {
        Kv map = Kv.create();
        map.put(Attribute.ATTRIBUTE_ID, attribute_id);
        map.put(Attribute.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(Attribute.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("attribute.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}