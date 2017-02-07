package com.shanghaichuangshi.service;

import com.shanghaichuangshi.constant.Key;
import com.shanghaichuangshi.type.FileType;
import com.shanghaichuangshi.util.FileUtil;
import com.shanghaichuangshi.util.Util;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class UploadService {

    private final FileService fileService = new FileService();

    public void upload(HttpServletRequest request, String request_user_id) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            try {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(2 * 1024 * 1024);
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    String fileName = item.getName();
                    if (fileName != null) {
                        String path = FileUtil.getWebRootPath() + "/" + Key.ASSETS + "/" + Key.UPLOAD + "/" + request_user_id;
                        String thumbnailPath = FileUtil.getWebRootPath() + "/" + Key.ASSETS + "/" + Key.UPLOAD + "/" + request_user_id + "/" + Key.THUMBNAIL;
                        String originalPath = FileUtil.getWebRootPath() + "/" + Key.ASSETS + "/" + Key.UPLOAD + "/" + request_user_id + "/" + Key.ORIGINAL;
                        String suffix = item.getName().substring(item.getName().lastIndexOf(".") + 1);
                        String name = Util.getRandomUUID() + "." + suffix;

                        if (FileUtil.createPath(path)) {
                            FileUtil.createPath(thumbnailPath);
                            FileUtil.createPath(originalPath);
                        }

                        File image = new File(originalPath, name);
                        item.write(image);

                        path = path + "/" + name;
                        thumbnailPath = thumbnailPath + "/" + name;
                        originalPath = originalPath + "/" + name;

                        com.shanghaichuangshi.model.File file = new com.shanghaichuangshi.model.File();
                        file.setRequest_user_id(request_user_id);

                        file.setFile_type(FileType.IMAGE.getKey());
                        file.setFile_name(item.getName());
                        file.setFile_suffix(suffix);
                        file.setFile_size((int) image.length());
                        file.setFile_path(path.replace(FileUtil.getWebRootPath(), ""));
                        file.setFile_thumbnail(thumbnailPath.replace(FileUtil.getWebRootPath(), ""));
                        file.setFile_original_path(originalPath.replace(FileUtil.getWebRootPath(), ""));
                        fileService.save(file);

                        FileUtil.resizeImage(image, suffix, thumbnailPath, 100);
                        FileUtil.resizeImage(image, suffix, path, 360);
                        FileUtil.resizeImage(image, suffix, originalPath, 0);
                    }
                }
            } catch (FileUploadException e) {
                throw new RuntimeException("FileUploadException: " + e.toString());
            } catch (Exception e) {
                throw new RuntimeException("Exception: " + e.toString());
            }
        } else {
            throw new RuntimeException("the enctype must be multipart/form-data");
        }
    }

}
