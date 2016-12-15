package com.shanghaichuangshi.annotation;

import com.shanghaichuangshi.type.ColType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface ColDefine {
    ColType type() default ColType.AUTO;

    int width() default 0;
}

