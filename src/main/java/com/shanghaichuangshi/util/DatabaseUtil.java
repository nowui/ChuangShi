package com.shanghaichuangshi.util;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.shanghaichuangshi.config.Config;
import com.shanghaichuangshi.config.Mysql;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DatabaseUtil {

    private static final Mysql mysql = new Mysql();
    private static final DruidDataSource druidDataSource = new DruidDataSource();
    private static final QueryRunner runner = new QueryRunner(druidDataSource);

    public static void init(Config config) {
        config.configMysql(mysql);

        druidDataSource.setDriverClassName(mysql.getDriver_class());
        druidDataSource.setUrl(mysql.getUrl());
        druidDataSource.setUsername(mysql.getUser_name());
        druidDataSource.setPassword(mysql.getPassword());
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(20);

        try {
            druidDataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }

        Slf4jLogFilter sql_log_filter = new Slf4jLogFilter();

        sql_log_filter.setConnectionLogEnabled(false);
        sql_log_filter.setStatementLogEnabled(false);
        sql_log_filter.setStatementExecutableSqlLogEnable(true);
        sql_log_filter.setResultSetLogEnabled(false);

        druidDataSource.getProxyFilters().add(sql_log_filter);
    }

    public static int count(String sql, List<Object> parameterList) {
        try {
            Number result = (Number) runner.query(sql, new MapListHandler(), parameterList.toArray());

            return result.intValue();
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static List<Map<String, Object>> list(String sql, List<Object> parameterList) {
        try {
            return runner.query(sql, new MapListHandler(), parameterList.toArray());
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static Map<String, Object> find(String sql, List<Object> parameterList) {
        try {
            List<Map<String, Object>> resultList = runner.query(sql, new MapListHandler(), parameterList.toArray());

            if (resultList.size() > 0) {
                return resultList.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static boolean update(String sql, List<Object> parameterList) {
        int result = 0;
        try {
            result = runner.update(sql, new MapListHandler(), parameterList.toArray());
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }

        return result >= 1;
    }

}
