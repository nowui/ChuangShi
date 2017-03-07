package com.shanghaichuangshi.dao;

import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class ResourceDao extends Dao {

    public int count(String resource_name) {
        JMap map = JMap.create();
        map.put(Resource.RESOURCE_NAME, resource_name);
        SqlPara sqlPara = Db.getSqlPara("resource.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Resource> list(String resource_name, Integer m, Integer n) {
        JMap map = JMap.create();
        map.put(Resource.RESOURCE_NAME, resource_name);
        map.put(Resource.M, m);
        map.put(Resource.N, n);
        SqlPara sqlPara = Db.getSqlPara("resource.list", map);

        return new Resource().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Resource find(String resource_id) {
        JMap map = JMap.create();
        map.put(Resource.RESOURCE_ID, resource_id);
        SqlPara sqlPara = Db.getSqlPara("resource.find", map);

        List<Resource> resourceList = new Resource().find(sqlPara.getSql(), sqlPara.getPara());
        if (resourceList.size() == 0) {
            return null;
        } else {
            return resourceList.get(0);
        }
    }

    public Resource save(Resource resource, String request_user_id) {
        resource.setResource_id(Util.getRandomUUID());
        resource.setSystem_create_user_id(request_user_id);
        resource.setSystem_create_time(new Date());
        resource.setSystem_update_user_id(request_user_id);
        resource.setSystem_update_time(new Date());
        resource.setSystem_status(true);

        resource.save();

        return resource;
    }

    public boolean update(Resource resource, String request_user_id) {
        resource.remove(Resource.SYSTEM_CREATE_USER_ID);
        resource.remove(Resource.SYSTEM_CREATE_TIME);
        resource.setSystem_update_user_id(request_user_id);
        resource.setSystem_update_time(new Date());
        resource.remove(Resource.SYSTEM_STATUS);

        return resource.update();
    }

    public boolean delete(String resource_id, String request_user_id) {
        JMap map = JMap.create();
        map.put(Resource.RESOURCE_ID, resource_id);
        map.put(Resource.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(Resource.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("resource.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}