package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.FileDao;
import com.shanghaichuangshi.model.File;

import java.util.List;

public class FileService extends Service {

    private final FileDao fileDao = new FileDao();

    public int count(File file) {
        return fileDao.count(file.getRequest_user_id());
    }

    public List<File> list(File file) {
        return fileDao.list(file.getFile_name(), file.getRequest_user_id(), file.getM(), file.getN());
    }

    public File find(File file) {
        return fileDao.find(file.getFile_id());
    }

    public void save(File file) {
        fileDao.save(file);
    }

    public void update(File file) {
        fileDao.update(file);
    }

    public void delete(File file) {
        fileDao.delete(file);
    }

}