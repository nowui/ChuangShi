package com.shanghaichuangshi.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.jfinal.kit.PropKit;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final DruidDataSource druidDataSource = new DruidDataSource();

    static {
        PropKit.use("Jdbc.properties");

        druidDataSource.setDriverClassName(PropKit.get("driverClass"));
        druidDataSource.setUrl(PropKit.get("jdbcUrl"));
        druidDataSource.setUsername(PropKit.get("user"));
        druidDataSource.setPassword(PropKit.get("password"));
        druidDataSource.setInitialSize(Integer.valueOf(PropKit.getInt("initialSize")));
        druidDataSource.setMinIdle(Integer.valueOf(PropKit.getInt("minIdle")));
        druidDataSource.setMaxActive(Integer.valueOf(PropKit.getInt("maxActivee")));

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
