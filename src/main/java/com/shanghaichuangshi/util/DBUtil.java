package com.shanghaichuangshi.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtil {

    private static final QueryRunner runner = new QueryRunner();

    public static Connection open() {
        String url = "";
        String username = "";
        String password = "";
        String driverClass = "";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, Object>> list(String sql, List<Object> parameterList) {
        Connection connection = open();

        try {
            return runner.query(connection, sql, new MapListHandler(), parameterList.toArray());
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        } finally{
            close(connection);
        }
    }

    public static Map<String, Object> find(String sql, List<Object> parameterList) {
        Connection connection = open();

        try {
            List<Map<String, Object>> resultList = runner.query(connection, sql, new MapListHandler(), parameterList.toArray());

            if (resultList.size() > 0) {
                return resultList.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        } finally{
            close(connection);
        }
    }

    public static int update(String sql, List<Object> parameterList) {
        Connection connection = open();

        try {
            return runner.update(connection, sql, new MapListHandler(), parameterList.toArray());
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        } finally{
            close(connection);
        }
    }

}
