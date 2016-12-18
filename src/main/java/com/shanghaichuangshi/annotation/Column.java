package com.shanghaichuangshi.annotation;

import com.shanghaichuangshi.type.ColumnType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface Column {
    ColumnType type() default ColumnType.AUTO;

    int width() default 0;

    String defaultValue() default "";

    String comment() default "";
}
