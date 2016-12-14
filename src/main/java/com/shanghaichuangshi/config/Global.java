package com.shanghaichuangshi.config;

import com.shanghaichuangshi.constant.Constant;

public class Global {

    private static final Constant constant = new Constant();

    public static void init(Config config) {
        config.configConstant(constant);
    }

    public static final Constant getConstant() {
        return constant;
    }

}
