package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnType;

public class Code extends Model<Code> {

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "表名")
    public static final String TABLE_NAME = "table_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "列名")
    public static final String COLUMN_NAME = "column_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "长度")
    public static final String CHARACTER_MAXIMN_LENGTH = "character_maximun_length";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "注释")
    public static final String COLUMN_COMMENT = "column_comment";

    public String getTable_name() {
        return getString(TABLE_NAME);
    }

    public String getColumn_name() {
        return getString(COLUMN_NAME);
    }

    public String getCharacter_maximn_length() {
        return getString(CHARACTER_MAXIMN_LENGTH);
    }

    public String getColumn_comment() {
        return getString(COLUMN_COMMENT);
    }

}
