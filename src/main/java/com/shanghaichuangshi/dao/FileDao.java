package com.shanghaichuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.model.File;
import com.shanghaichuangshi.util.CacheUtil;
import com.shanghaichuangshi.util.Util;

import java.util.Date;
import java.util.List;

public class FileDao extends Dao {

    private final String FILE_CACHE = "file_cache";

    public int count(String file_name, String file_type, String request_user_id) {
        Kv map = Kv.create();
        map.put(File.FILE_NAME, file_name);
        map.put(File.FILE_TYPE, file_type);
        map.put(Constant.REQUEST_USER_ID, request_user_id);
        SqlPara sqlPara = Db.getSqlPara("file.count", map);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<File> list(String file_name, String file_type, String request_user_id, Integer m, Integer n) {
        Kv map = Kv.create();
        map.put(File.FILE_NAME, file_name);
        map.put(File.FILE_TYPE, file_type);
        map.put(Constant.REQUEST_USER_ID, request_user_id);
        map.put(File.M, m);
        map.put(File.N, n);
        SqlPara sqlPara = Db.getSqlPara("file.list", map);

        return new File().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public File find(String file_id) {
        File file = CacheUtil.get(FILE_CACHE, file_id);

        if (file == null) {
            Kv map = Kv.create();
            map.put(File.FILE_ID, file_id);
            SqlPara sqlPara = Db.getSqlPara("file.find", map);

            List<File> fileList = new File().find(sqlPara.getSql(), sqlPara.getPara());
            if (fileList.size() == 0) {
                file = null;
            } else {
                file = fileList.get(0);

                CacheUtil.put(FILE_CACHE, file_id, file);
            }
        }

        return file;
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
        CacheUtil.remove(FILE_CACHE, file.getFile_id());

        file.remove(File.SYSTEM_CREATE_USER_ID);
        file.remove(File.SYSTEM_CREATE_TIME);
        file.setSystem_update_user_id(request_user_id);
        file.setSystem_update_time(new Date());
        file.remove(File.SYSTEM_STATUS);

        return file.update();
    }

    public boolean delete(String file_id, String request_user_id) {
        CacheUtil.remove(FILE_CACHE, file_id);

        Kv map = Kv.create();
        map.put(File.FILE_ID, file_id);
        map.put(File.SYSTEM_UPDATE_USER_ID, request_user_id);
        map.put(File.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("file.delete", map);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}