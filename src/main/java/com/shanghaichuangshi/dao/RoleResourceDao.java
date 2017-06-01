package com.shanghaichuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.model.RoleResource;
import com.shanghaichuangshi.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleResourceDao extends Dao {

    public List<RoleResource> list(String role_id) {
        Kv map = Kv.create();
        map.put(RoleResource.ROLE_ID, role_id);
        SqlPara sqlPara = Db.getSqlPara("role_resource.list", map);

        return new RoleResource().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<RoleResource> listByRole_key(String role_key) {
        Kv map = Kv.create();
        map.put(RoleResource.ROLE_KEY, role_key);
        SqlPara sqlPara = Db.getSqlPara("role_resource.listByRole_key", map);

        return new RoleResource().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public void save(List<RoleResource> roleResourceList, String role_id, String request_user_id) {
        if (roleResourceList.size() == 0) {
            return;
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("role_resource.save", map);

        List<Object[]> parameterList = new ArrayList<Object[]>();
        for(RoleResource roleResource : roleResourceList) {

            List<Object> objectList = new ArrayList<Object>();
            objectList.add(Util.getRandomUUID());
            objectList.add(roleResource.getRole_id());
            objectList.add(roleResource.getResource_id());
            objectList.add(request_user_id);
            objectList.add(new Date());
            objectList.add(request_user_id);
            objectList.add(new Date());
            objectList.add(true);
            parameterList.add(objectList.toArray());
        }

        int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(parameterList), Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("角色资源保存不成功");
            }
        }
    }

    public void delete(List<String> roleResourceIdList, String role_id, String request_user_id) {
        if (roleResourceIdList.size() == 0) {
            return;
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("role_resource.delete", map);

        List<Object[]> parameterList = new ArrayList<Object[]>();
        for(String roleResourceId : roleResourceIdList) {

            List<Object> objectList = new ArrayList<Object>();
            objectList.add(request_user_id);
            objectList.add(new Date());
            objectList.add(roleResourceId);
            parameterList.add(objectList.toArray());
        }

        int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(parameterList), Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("角色资源删除不成功");
            }
        }
    }

}