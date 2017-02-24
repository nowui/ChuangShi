package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.FileDao;
import com.shanghaichuangshi.model.File;

import java.util.List;

public class FileService extends Service {

    private static final FileDao fileDao = new FileDao();

    public int count(File file, String request_user_id) {
        return fileDao.count(file.getFile_name(), request_user_id);
    }

    public List<File> list(File file, String request_user_id, int m, int n) {
        return fileDao.list(file.getFile_name(), request_user_id, m, n);
    }

    public File find(String file_id) {
        return fileDao.find(file_id);
    }

    public File save(File file, String request_user_id) {
        return fileDao.save(file, request_user_id);
    }

    public boolean update(File file, String request_user_id) {
        return fileDao.update(file, request_user_id);
    }

    public boolean delete(File file, String request_user_id) {
        return fileDao.delete(file.getFile_id(), request_user_id);
    }

}