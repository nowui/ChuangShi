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

    public File find(File file) {
        return fileDao.find(file.getFile_id());
    }

    public void save(File file, String request_user_id) {
        fileDao.save(file, request_user_id);
    }

    public void update(File file, String request_user_id) {
        fileDao.update(file, request_user_id);
    }

    public void delete(File file, String request_user_id) {
        fileDao.delete(file.getFile_id(), request_user_id);
    }

}