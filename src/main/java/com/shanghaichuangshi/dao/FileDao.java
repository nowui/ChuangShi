package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.File;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.Util;

import java.util.List;

public class FileDao extends Dao {

    public int count(String request_user_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append(File.TABLE_FILE).append(" ");
        dynamicSQL.append("WHERE ").append(File.TABLE_FILE).append(".").append(File.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(File.TABLE_FILE).append(".").append(File.SYSTEM_CREATE_USER_ID).append(" = ? ", request_user_id);

        return DatabaseUtil.count(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<File> list(String file_name, String request_user_id, Integer m, Integer n) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(File.TABLE_FILE).append(".").append(File.FILE_ID).append(", ");
        dynamicSQL.append(File.TABLE_FILE).append(".").append(File.FILE_THUMBNAIL).append(", ");
        dynamicSQL.append(File.TABLE_FILE).append(".").append(File.FILE_PATH).append(" ");
        dynamicSQL.append("FROM ").append(File.TABLE_FILE).append(" ");
        dynamicSQL.append("WHERE ").append(File.TABLE_FILE).append(".").append(File.SYSTEM_STATUS).append(" = ? ", true);
        if (!Util.isNullOrEmpty(file_name)) {
            dynamicSQL.append("AND ").append(File.TABLE_FILE).append(".").append(File.FILE_NAME).append(" LIKE ? ", "%" + file_name + "%");
        }
        dynamicSQL.append("AND ").append(File.TABLE_FILE).append(".").append(File.SYSTEM_CREATE_USER_ID).append(" = ? ", request_user_id);
        dynamicSQL.append("ORDER BY ").append(File.TABLE_FILE).append(".").append(File.SYSTEM_CREATE_TIME).append(" DESC ");
        dynamicSQL.append("LIMIT ?, ? ", m, n);

        return (List<File>) DatabaseUtil.list(dynamicSQL.getSql(), dynamicSQL.getParameterList(), File.class);
    }

    public File find(String file_id) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append(File.TABLE_FILE).append(".* ");
        dynamicSQL.append("FROM ").append(File.TABLE_FILE).append(" ");
        dynamicSQL.append("WHERE ").append(File.TABLE_FILE).append(".").append(File.SYSTEM_STATUS).append(" = ? ", true);
        dynamicSQL.append("AND ").append(File.TABLE_FILE).append(".").append(File.FILE_ID).append(" = ? ", file_id);

        return (File) DatabaseUtil.find(dynamicSQL.getSql(), dynamicSQL.getParameterList(), File.class);
    }

    public boolean save(File file) {
        file.setFile_id(Util.getRandomUUID());

        return file.save();
    }

    public boolean update(File file) {
        return file.update();
    }

    public boolean delete(File file) {
        return file.delete();
    }

}