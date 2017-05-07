package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.FileDao;
import com.shanghaichuangshi.model.File;
import com.shanghaichuangshi.type.FileType;

import java.util.List;

public class FileService extends Service {

    private final FileDao fileDao = new FileDao();

    public int count(String file_name, String file_type, String request_user_id) {
        return fileDao.count(file_name, file_type, request_user_id);
    }

    public List<File> list(String file_name, String file_type, String request_user_id, int m, int n) {
        return fileDao.list(file_name, file_type, request_user_id, m, n);
    }

    public File find(String file_id) {
        return fileDao.find(file_id);
    }

    public File save(File file, String request_user_id) {
        return fileDao.save(file, request_user_id);
    }

    public File videoSave(String file_name, String file_path, String file_image, String request_user_id) {
        File file = new File();
        file.setFile_type(FileType.VIDEO.getKey());
        file.setFile_name(file_name);
        file.setFile_suffix(file_path.substring(file_path.lastIndexOf(".") + 1, file_path.length()));
        file.setFile_size(0);
        file.setFile_path(file_path);
        file.setFile_thumbnail_path(file_path);
        file.setFile_original_path(file_path);
        file.setFile_image(file_image);

        return fileDao.save(file, request_user_id);
    }

    public boolean update(File file, String request_user_id) {
        return fileDao.update(file, request_user_id);
    }

    public boolean delete(File file, String request_user_id) {
        return fileDao.delete(file.getFile_id(), request_user_id);
    }

}