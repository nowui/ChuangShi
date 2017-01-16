package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.type.ColumnType;

public class Attribute extends Model<Attribute> {

    @Table()
    public static final String TABLE_ATTRIBUTE = "table_attribute";

    @Id
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "属性编号")
    public static final String ATTRIBUTE_ID = "attribute_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "属性名称")
    public static final String ATTRIBUTE_NAME = "attribute_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "属性输入类型")
    public static final String ATTRIBUTE_INPUT_TYPE = "attribute_input_type";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "属性初始化值")
    public static final String ATTRIBUTE_DEFAULT_VALUE = "attribute_default_value";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "属性类型")
    public static final String ATTRIBUTE_TYPE = "attribute_type";

    @Column(type = ColumnType.INT, length = 3, comment = "属性排序")
    public static final String ATTRIBUTE_SORT = "attribute_sort";

    public String getAttribute_id() {
        return getString(ATTRIBUTE_ID);
    }

    public void setAttribute_id(String attribute_id) {
        set(ATTRIBUTE_ID, attribute_id);
    }

    public String getAttribute_name() {
        return getString(ATTRIBUTE_NAME);
    }

    public void setAttribute_name(String attribute_name) {
        set(ATTRIBUTE_NAME, attribute_name);
    }

    public String getAttribute_input_type() {
        return getString(ATTRIBUTE_INPUT_TYPE);
    }

    public void setAttribute_input_type(String attribute_input_type) {
        set(ATTRIBUTE_INPUT_TYPE, attribute_input_type);
    }

    public String getAttribute_default_value() {
        return getString(ATTRIBUTE_DEFAULT_VALUE);
    }

    public void setAttribute_default_value(String attribute_default_value) {
        set(ATTRIBUTE_DEFAULT_VALUE, attribute_default_value);
    }

    public String getAttribute_type() {
        return getString(ATTRIBUTE_TYPE);
    }

    public void setAttribute_type(String attribute_type) {
        set(ATTRIBUTE_TYPE, attribute_type);
    }

    public Integer getAttribute_sort() {
        return getInteger(ATTRIBUTE_SORT);
    }

    public void setAttribute_sort(Integer attribute_sort) {
        set(ATTRIBUTE_SORT, attribute_sort);
    }

}