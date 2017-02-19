package com.shanghaichuangshi;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.shanghaichuangshi.controller.AdminController;
import com.shanghaichuangshi.controller.CategoryController;
import com.shanghaichuangshi.controller.CodeController;
import com.shanghaichuangshi.controller.LogController;
import com.shanghaichuangshi.interceptor.GlobalActionInterceptor;
import com.shanghaichuangshi.model.*;

public class WebConfig extends JFinalConfig {

    public static void main(String[] args) {
         JFinal.start("WebRoot", 80, "/");
    }

    public void configConstant(Constants constants) {
        constants.setDevMode(false);
        constants.setViewType(ViewType.JSP);
        constants.setError404View("/error.jsp");
    }

    public void configRoute(Routes routes) {
        routes.add("/admin", AdminController.class);
        routes.add("/log", LogController.class);
        routes.add("/category", CategoryController.class);
        routes.add("/code", CodeController.class);
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {
        PropKit.use("Jdbc.properties");

        final String URL = PropKit.get("jdbcUrl");
        final String USERNAME = PropKit.get("user");
        final String PASSWORD = PropKit.get("password");
        final Integer INITIALSIZE = PropKit.getInt("initialSize");
        final Integer MIDIDLE = PropKit.getInt("minIdle");
        final Integer MAXACTIVEE = PropKit.getInt("maxActivee");

        DruidPlugin druidPlugin = new DruidPlugin(URL, USERNAME, PASSWORD);
        druidPlugin.set(INITIALSIZE, MIDIDLE, MAXACTIVEE);
        druidPlugin.setFilters("stat,wall");
        plugins.add(druidPlugin);

        Slf4jLogFilter sql_log_filter = new Slf4jLogFilter();
        sql_log_filter.setConnectionLogEnabled(false);
        sql_log_filter.setStatementLogEnabled(false);
        sql_log_filter.setStatementExecutableSqlLogEnable(true);
        sql_log_filter.setResultSetLogEnabled(false);
        druidPlugin.addFilter(sql_log_filter);

        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.setBaseSqlTemplatePath(PathKit.getRootClassPath() + "/sql/");
        activeRecordPlugin.addSqlTemplate("code.sql");

        activeRecordPlugin.addMapping("table_admin", "admin_id", Admin.class);
        activeRecordPlugin.addSqlTemplate("admin.sql");
        activeRecordPlugin.addMapping("table_authorization", "authorization_id", Authorization.class);
        activeRecordPlugin.addSqlTemplate("authorization.sql");
        activeRecordPlugin.addMapping("table_category", "category_id", Category.class);
        activeRecordPlugin.addSqlTemplate("category.sql");
        activeRecordPlugin.addMapping("table_log", "log_id", Log.class);
        activeRecordPlugin.addSqlTemplate("log.sql");
        activeRecordPlugin.addMapping("table_user", "user_id", User.class);
        activeRecordPlugin.addSqlTemplate("user.sql");
        activeRecordPlugin.addMapping("table_file", "file_id", File.class);
        activeRecordPlugin.addSqlTemplate("file.sql");

        plugins.add(activeRecordPlugin);
    }

    public void configInterceptor(Interceptors interceptors) {
        interceptors.addGlobalActionInterceptor(new GlobalActionInterceptor());
    }

    public void configHandler(Handlers handlers) {

    }

}