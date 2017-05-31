package com.shanghaichuangshi.service;

import com.jfinal.kit.FileKit;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.shanghaichuangshi.cache.FileCache;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.model.File;
import com.shanghaichuangshi.type.FileType;
import com.shanghaichuangshi.util.FileUtil;
import com.shanghaichuangshi.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadService extends Service {

    private final FileCache fileCache = new FileCache();

    public List<Map<String, Object>> image(List<UploadFile> uploadFileList, String request_user_id) {
        String path = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + request_user_id;
        String thumbnailPath = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + request_user_id + "/" + Constant.THUMBNAIL;
        String originalPath = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + request_user_id + "/" + Constant.ORIGINAL;

        FileUtil.createPath(path);
        FileUtil.createPath(thumbnailPath);
        FileUtil.createPath(originalPath);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (UploadFile uploadFile : uploadFileList) {
            String suffix = uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf(".") + 1);
            String name = Util.getRandomUUID() + "." + suffix;

            path = path + "/" + name;
            thumbnailPath = thumbnailPath + "/" + name;
            originalPath = originalPath + "/" + name;

            FileUtil.resizeImage(uploadFile.getFile(), suffix, thumbnailPath, 100);
            FileUtil.resizeImage(uploadFile.getFile(), suffix, path, 360);
            FileUtil.resizeImage(uploadFile.getFile(), suffix, originalPath, 0);

            FileKit.delete(uploadFile.getFile());

            File file = new File();
            file.setFile_type(FileType.IMAGE.getKey());
            file.setFile_name(name);
            file.setFile_suffix(suffix);
            file.setFile_size((int) uploadFile.getFile().length());
            file.setFile_path(path.replace(PathKit.getWebRootPath(), ""));
            file.setFile_thumbnail_path(thumbnailPath.replace(PathKit.getWebRootPath(), ""));
            file.setFile_original_path(originalPath.replace(PathKit.getWebRootPath(), ""));
            file.setFile_image(originalPath.replace(PathKit.getWebRootPath(), ""));

            File f = fileCache.save(file, request_user_id);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(File.FILE_ID, f.getFile_id());
            map.put(File.FILE_NAME, f.getFile_name());
            map.put(File.FILE_PATH, f.getFile_original_path());
            list.add(map);
        }

        return list;
    }

}
