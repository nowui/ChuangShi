package com.shanghaichuangshi;

import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.shanghaichuangshi.controller.*;
import com.shanghaichuangshi.model.*;

import java.util.List;

public class WebConfig {

    public static Routes configRoute(Routes routes) {
        routes.add("/admin", AdminController.class);
        routes.add("/authorization", AuthorizationController.class);
        routes.add("/log", LogController.class);
        routes.add("/category", CategoryController.class);
        routes.add("/code", CodeController.class);
        routes.add("/attribute", AttributeController.class);
        routes.add("/resource", ResourceController.class);
        routes.add("/role", RoleController.class);
        routes.add("/file", FileController.class);
        routes.add("/config", ConfigController.class);

        return routes;
    }

    public static ActiveRecordPlugin configActiveRecordPlugin(ActiveRecordPlugin activeRecordPlugin) {
        activeRecordPlugin.addMapping("table_admin", "admin_id", Admin.class);
        activeRecordPlugin.addMapping("table_authorization", "authorization_id", Authorization.class);
        activeRecordPlugin.addMapping("table_category", "category_id", Category.class);
        activeRecordPlugin.addMapping("table_log", "log_id", Log.class);
        activeRecordPlugin.addMapping("table_user", "user_id", User.class);
        activeRecordPlugin.addMapping("table_file", "file_id", File.class);
        activeRecordPlugin.addMapping("table_attribute", "attribute_id", Attribute.class);
        activeRecordPlugin.addMapping("table_resource", "resource_id", Resource.class);
        activeRecordPlugin.addMapping("table_role", "role_id", Role.class);
        activeRecordPlugin.addMapping("table_role_resource", "role_resource_id", RoleResource.class);

        return activeRecordPlugin;
    }

    public static List<String> configUncheckUrlList(List<String> uncheckUrlList) {

        return uncheckUrlList;
    }

    public static List<String> configUncheckTokenUrlList(List<String> uncheckTokenUrlList) {

        return uncheckTokenUrlList;
    }

    public static List<String> configUncheckRequestUserIdUrlList(List<String> uncheckRequestUserIdUrlList) {

        return uncheckRequestUserIdUrlList;
    }

    public static List<String> configUncheckParameterUrlList(List<String> uncheckParameterUrlList) {

        return uncheckParameterUrlList;
    }

    public static List<String> configUncheckHeaderUrlList(List<String> uncheckHeaderUrlList) {

        return uncheckHeaderUrlList;
    }

}
