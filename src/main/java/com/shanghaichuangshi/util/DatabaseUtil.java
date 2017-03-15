package com.shanghaichuangshi.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.shanghaichuangshi.constant.Jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final DruidDataSource druidDataSource = new DruidDataSource();

    static {
        druidDataSource.setDriverClassName(Jdbc.driver_class);
        druidDataSource.setUrl(Jdbc.jdbc_url);
        druidDataSource.setUsername(Jdbc.user);
        druidDataSource.setPassword(Jdbc.password);
        druidDataSource.setInitialSize(Jdbc.initial_size);
        druidDataSource.setMinIdle(Jdbc.min_idle);
        druidDataSource.setMaxActive(Jdbc.max_activee);

        try {
            druidDataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static Connection getConnection() {
        try {
            return druidDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

}
