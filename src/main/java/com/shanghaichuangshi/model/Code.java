package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnType;

public class Code extends Model<Code> {

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "表名")
    public static final String TABLE_NAME = "table_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "列名")
    public static final String COLUMN_NAME = "column_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "类型")
    public static final String DATA_TYPE = "data_type";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "长度")
    public static final String CHARACTER_MAXIMM_LENGTH = "character_maximum_length";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "类型")
    public static final String COLUMN_TYPE = "column_type";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "注释")
    public static final String COLUMN_COMMENT = "column_comment";

    public String getTable_name() {
        return getString(TABLE_NAME);
    }

    public String getColumn_name() {
        return getString(COLUMN_NAME);
    }

    public String getData_type() {
        return getString(DATA_TYPE);
    }

    public String getCharacter_maximM_length() {
        String length = getColumn_type().replace(getString(CHARACTER_MAXIMM_LENGTH), "").replace("(", "").replace(")", "");

        System.out.println(length);

        if (length.equals("")) {
            length = "0";
        }
        return length;
    }

    public String getColumn_type() {
        return getString(COLUMN_TYPE);
    }

    public String getColumn_comment() {
        return getString(COLUMN_COMMENT);
    }

}
