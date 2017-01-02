package com.shanghaichuangshi.util;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.shanghaichuangshi.handler.ModelListHandler;
import com.shanghaichuangshi.model.Model;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUtil {

    private static final DruidDataSource druidDataSource = new DruidDataSource();
    private static final QueryRunner runner = new QueryRunner(druidDataSource);
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {
        PropertiesUtil.load("Jdbc.properties");

        druidDataSource.setDriverClassName(PropertiesUtil.getProperty("driverClass"));
        druidDataSource.setUrl(PropertiesUtil.getProperty("jdbcUrl"));
        druidDataSource.setUsername(PropertiesUtil.getProperty("user"));
        druidDataSource.setPassword(PropertiesUtil.getProperty("password"));
        druidDataSource.setInitialSize(Integer.valueOf(PropertiesUtil.getProperty("initialSize")));
        druidDataSource.setMinIdle(Integer.valueOf(PropertiesUtil.getProperty("minIdle")));
        druidDataSource.setMaxActive(Integer.valueOf(PropertiesUtil.getProperty("maxActivee")));

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

    public static Connection getConnection() {
        try {
            return druidDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static void start() {
        Connection connection = threadLocal.get();

        if (connection == null) {
            try {
                connection = druidDataSource.getConnection();

                threadLocal.remove();
                threadLocal.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException("SQLException: ", e);
            }
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static void commit() {
        Connection connection = threadLocal.get();

        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static void rollback() {
        Connection connection = threadLocal.get();

        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static void close() {
        Connection connection = threadLocal.get();

        try {
            if (connection != null) {
                connection.close();

                threadLocal.remove();
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static int count(String sql, List<Object> parameterList) {
        try {
            Number result = (Number) runner.query(sql, new MapListHandler(), parameterList.toArray());

            return result.intValue();
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

//    public static List<Map<String, Object>> list(String sql, List<Object> parameterList) {
//        try {
//            return runner.query(sql, new MapListHandler(), parameterList.toArray());
//        } catch (SQLException e) {
//            throw new RuntimeException("SQLException: ", e);
//        }
//    }

    public static List<? extends Model> list(String sql, List<Object> parameterList, Class<? extends Model> modelClass) {
        try {
            return runner.query(sql, new ModelListHandler(modelClass), parameterList.toArray());
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static Model<? extends Model> find(String sql, List<Object> parameterList, Class<? extends Model> modelClass) {
        try {
            List<? extends Model> resultList = runner.query(sql, new ModelListHandler(modelClass), parameterList.toArray());

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
