package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.FileDao;
import com.shanghaichuangshi.model.File;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.List;

public class FileCache extends Cache {

    public static final String FILE_BY_FILE_ID_CACHE = "file_by_file_id_cache";

    private FileDao fileDao = new FileDao();

    public int count(String file_name, String file_type, String request_user_id) {
        return fileDao.count(file_name, file_type, request_user_id);
    }

    public List<File> list(String file_name, String file_type, String request_user_id, Integer m, Integer n) {
        return fileDao.list(file_name, file_type, request_user_id, m, n);
    }

    public File find(String file_id) {
        File file = CacheUtil.get(FILE_BY_FILE_ID_CACHE, file_id);

        if (file == null) {
            file = fileDao.find(file_id);

            CacheUtil.put(FILE_BY_FILE_ID_CACHE, file_id, file);
        }

        return file;
    }

    public File save(File file, String request_user_id) {
        return fileDao.save(file, request_user_id);
    }

    public boolean update(File file, String request_user_id) {
        CacheUtil.remove(FILE_BY_FILE_ID_CACHE, file.getFile_id());

        return fileDao.update(file, request_user_id);
    }

    public boolean delete(String file_id, String request_user_id) {
        CacheUtil.remove(FILE_BY_FILE_ID_CACHE, file_id);

        return fileDao.delete(file_id, request_user_id);
    }

}