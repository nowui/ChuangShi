package com.shanghaichuangshi.dao;

import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.model.File;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class FileDao extends Dao {

    public int count(String file_name, String request_user_id) {
        JMap map = JMap.create();
        map.put(Constant.REQUEST_USER_ID, request_user_id);
        SqlPara sqlPara = Db.getSqlPara("file.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<File> list(String file_name, String request_user_id, Integer m, Integer n) {
        JMap map = JMap.create();
        map.put(File.FILE_NAME, file_name);
        map.put(Constant.REQUEST_USER_ID, request_user_id);
        map.put(Authorization.M, m);
        map.put(Authorization.N, n);
        SqlPara sqlPara = Db.getSqlPara("file.list", map);

        return new File().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public File find(String file_id) {
        JMap map = JMap.create();
        map.put(File.FILE_ID, file_id);
        SqlPara sqlPara = Db.getSqlPara("file.find", map);

        List<File> fileList = new File().find(sqlPara.getSql(), sqlPara.getPara());
        if (fileList.size() == 0) {
            return null;
        } else {
            return fileList.get(0);
        }
    }

    public File save(File file, String request_user_id) {
        file.setFile_id(Util.getRandomUUID());
        file.setSystem_create_user_id(request_user_id);
        file.setSystem_create_time(new Date());
        file.setSystem_update_user_id(request_user_id);
        file.setSystem_update_time(new Date());
        file.setSystem_status(true);

        file.save();

        return file;
    }

    public boolean update(File file, String request_user_id) {
        file.remove(File.SYSTEM_CREATE_USER_ID);
        file.remove(File.SYSTEM_CREATE_TIME);
        file.setSystem_update_user_id(request_user_id);
        file.setSystem_update_time(new Date());
        file.remove(File.SYSTEM_STATUS);

        return file.update();
    }

    public boolean delete(String file_id, String request_user_id) {
        JMap map = JMap.create();
        map.put(File.FILE_ID, file_id);
        map.put(File.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(File.SYSTEM_UPDATE_TIME, new Date());
        map.put(File.SYSTEM_STATUS, false);
        SqlPara sqlPara = Db.getSqlPara("file.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) == 1;
    }

}