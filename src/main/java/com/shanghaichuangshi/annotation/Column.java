package com.shanghaichuangshi.annotation;

import com.shanghaichuangshi.type.ColumnTypeEnum;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface Column {
    ColumnTypeEnum type() default ColumnTypeEnum.VARCHAR;

    int length() default 0;

    String defaultValue() default "";

    String comment() default "";

    boolean findable() default true;

    boolean updatable() default true;
}