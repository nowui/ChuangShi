package com.shanghaichuangshi.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.shanghaichuangshi.config.Global;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtil {

    private static DruidDataSource druidDataSource;
    //    private static final QueryRunner runner = new QueryRunner();
    private static QueryRunner runner;

    private static DruidDataSource getDataSource() {
        if (druidDataSource == null) {
            druidDataSource = new DruidDataSource();
            druidDataSource.setDriverClassName(Global.getConstant().getDriverClass());
            druidDataSource.setUrl(Global.getConstant().getUrl());
            druidDataSource.setUsername(Global.getConstant().getUsername());
            druidDataSource.setPassword(Global.getConstant().getPassword());
            druidDataSource.setInitialSize(5);
            druidDataSource.setMinIdle(1);
            druidDataSource.setMaxActive(20);
            try {
                druidDataSource.setFilters("stat,wall");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            druidDataSource.setPoolPreparedStatements(false);
        }

        return druidDataSource;
    }

    private static QueryRunner getRunner() {
        if (runner == null) {
            runner = new QueryRunner(getDataSource());
        }

        return runner;
    }

    public static List<Map<String, Object>> list(String sql, List<Object> parameterList) {
        try {
            return getRunner().query(sql, new MapListHandler(), parameterList.toArray());
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static Map<String, Object> find(String sql, List<Object> parameterList) {
        try {
            List<Map<String, Object>> resultList = getRunner().query(sql, new MapListHandler(), parameterList.toArray());

            if (resultList.size() > 0) {
                return resultList.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static int update(String sql, List<Object> parameterList) {
        try {
            return getRunner().update(sql, new MapListHandler(), parameterList.toArray());
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

//    private static Connection open() {
//        Connection connection = null;
//
//        try {
//            Class.forName(Global.getConstant().getDriverClass());
//            connection = DriverManager.getConnection(Global.getConstant().getUrl(), Global.getConstant().getUsername(), Global.getConstant().getPassword());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        return connection;
//    }
//
//    private static void close(Connection connection) {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static List<Map<String, Object>> list(String sql, List<Object> parameterList) {
//        Connection connection = open();
//
//        try {
//            return runner.query(connection, sql, new MapListHandler(), parameterList.toArray());
//        } catch (SQLException e) {
//            throw new RuntimeException("SQLException: ", e);
//        } finally{
//            close(connection);
//        }
//    }
//
//    public static Map<String, Object> find(String sql, List<Object> parameterList) {
//        Connection connection = open();
//
//        try {
//            List<Map<String, Object>> resultList = runner.query(connection, sql, new MapListHandler(), parameterList.toArray());
//
//            if (resultList.size() > 0) {
//                return resultList.get(0);
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("SQLException: ", e);
//        } finally{
//            close(connection);
//        }
//    }
//
//    public static int update(String sql, List<Object> parameterList) {
//        Connection connection = open();
//
//        try {
//            return runner.update(connection, sql, new MapListHandler(), parameterList.toArray());
//        } catch (SQLException e) {
//            throw new RuntimeException("SQLException: ", e);
//        } finally{
//            close(connection);
//        }
//    }

}
