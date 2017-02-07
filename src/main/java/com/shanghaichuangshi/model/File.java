package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.type.ColumnType;

public class File extends Model<File> {

    @Table()
    public static final String TABLE_FILE = "table_file";

    @Id
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String FILE_ID = "file_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String FILE_TYPE = "file_type";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "")
    public static final String FILE_NAME = "file_name";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String FILE_SUFFIX = "file_suffix";

    @Column(type = ColumnType.INT, length = 11, comment = "")
    public static final String FILE_SIZE = "file_size";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "")
    public static final String FILE_PATH = "file_path";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "")
    public static final String FILE_THUMBNAIL = "file_thumbnail";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "")
    public static final String FILE_ORIGINAL_PATH = "file_original_path";

    public String getFile_id() {
        return getString(FILE_ID);
    }

    public void setFile_id(String file_id) {
        set(FILE_ID, file_id);
    }

    public String getFile_type() {
        return getString(FILE_TYPE);
    }

    public void setFile_type(String file_type) {
        set(FILE_TYPE, file_type);
    }

    public String getFile_name() {
        return getString(FILE_NAME);
    }

    public void setFile_name(String file_name) {
        set(FILE_NAME, file_name);
    }

    public String getFile_suffix() {
        return getString(FILE_SUFFIX);
    }

    public void setFile_suffix(String file_suffix) {
        set(FILE_SUFFIX, file_suffix);
    }

    public Integer getFile_size() {
        return getInteger(FILE_SIZE);
    }

    public void setFile_size(Integer file_size) {
        set(FILE_SIZE, file_size);
    }

    public String getFile_path() {
        return getString(FILE_PATH);
    }

    public void setFile_path(String file_path) {
        set(FILE_PATH, file_path);
    }

    public String getFile_thumbnail() {
        return getString(FILE_THUMBNAIL);
    }

    public void setFile_thumbnail(String file_thumbnail) {
        set(FILE_THUMBNAIL, file_thumbnail);
    }

    public String getFile_original_path() {
        return getString(FILE_ORIGINAL_PATH);
    }

    public void setFile_original_path(String file_original_path) {
        set(FILE_ORIGINAL_PATH, file_original_path);
    }

}