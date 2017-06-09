package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnTypeEnum;

public class File extends Model<File> {

    @Column(type = ColumnTypeEnum.VARCHAR, length = 32, comment = "文件编号")
    public static final String FILE_ID = "file_id";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 10, comment = "文件类型")
    public static final String FILE_TYPE = "file_type";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 100, comment = "文件名称")
    public static final String FILE_NAME = "file_name";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 10, comment = "文件后缀")
    public static final String FILE_SUFFIX = "file_suffix";

    @Column(type = ColumnTypeEnum.INT, length = 11, comment = "文件大小")
    public static final String FILE_SIZE = "file_size";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 200, comment = "文件路径")
    public static final String FILE_PATH = "file_path";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 200, comment = "文件路径")
    public static final String FILE_THUMBNAIL_PATH = "file_thumbnail_path";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 200, comment = "文件路径")
    public static final String FILE_ORIGINAL_PATH = "file_original_path";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 200, comment = "文件封面")
    public static final String FILE_IMAGE = "file_image";

    public String getFile_id() {
        return getStr(FILE_ID);
    }

    public void setFile_id(String file_id) {
        set(FILE_ID, file_id);
    }

    public String getFile_type() {
        return getStr(FILE_TYPE);
    }

    public void setFile_type(String file_type) {
        set(FILE_TYPE, file_type);
    }

    public String getFile_name() {
        return getStr(FILE_NAME);
    }

    public void setFile_name(String file_name) {
        set(FILE_NAME, file_name);
    }

    public String getFile_suffix() {
        return getStr(FILE_SUFFIX);
    }

    public void setFile_suffix(String file_suffix) {
        set(FILE_SUFFIX, file_suffix);
    }

    public Integer getFile_size() {
        return getInt(FILE_SIZE);
    }

    public void setFile_size(Integer file_size) {
        set(FILE_SIZE, file_size);
    }

    public String getFile_path() {
        return getStr(FILE_PATH);
    }

    public void setFile_path(String file_path) {
        set(FILE_PATH, file_path);
    }

    public String getFile_thumbnail_path() {
        return getStr(FILE_THUMBNAIL_PATH);
    }

    public void setFile_thumbnail_path(String file_thumbnail_path) {
        set(FILE_THUMBNAIL_PATH, file_thumbnail_path);
    }

    public String getFile_original_path() {
        return getStr(FILE_ORIGINAL_PATH);
    }

    public void setFile_original_path(String file_original_path) {
        set(FILE_ORIGINAL_PATH, file_original_path);
    }

    public String getFile_image() {
        return getStr(FILE_IMAGE);
    }

    public void setFile_image(String file_image) {
        set(FILE_IMAGE, file_image);
    }

}