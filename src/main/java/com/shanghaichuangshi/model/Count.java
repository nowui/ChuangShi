package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnTypeEnum;

public class Count extends Model<Count> {

    @Column(type = ColumnTypeEnum.VARCHAR, length = 32, comment = "统计编号")
    public static final String COUNT_ID = "count_id";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 32, comment = "外键编号")
    public static final String OBJECT_ID = "object_id";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 15, comment = "统计类型")
    public static final String COUNT_TYPE = "count_type";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 15, comment = "统计键值")
    public static final String COUNT_KEY = "count_key";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 250, comment = "统计数值")
    public static final String COUNT_VALUE = "count_value";

    public String getCount_id() {
        return getStr(COUNT_ID);
    }

    public void setCount_id(String count_id) {
        set(COUNT_ID, count_id);
    }

    public String getObject_id() {
        return getStr(OBJECT_ID);
    }

    public void setObject_id(String object_id) {
        set(OBJECT_ID, object_id);
    }

    public String getCount_type() {
        return getStr(COUNT_TYPE);
    }

    public void setCount_type(String count_type) {
        set(COUNT_TYPE, count_type);
    }

    public String getCount_key() {
        return getStr(COUNT_KEY);
    }

    public void setCount_key(String count_key) {
        set(COUNT_KEY, count_key);
    }

    public String getCount_value() {
        return getStr(COUNT_VALUE);
    }

    public void setCount_value(String count_value) {
        set(COUNT_VALUE, count_value);
    }

}