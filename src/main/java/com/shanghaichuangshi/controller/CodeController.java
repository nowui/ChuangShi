package com.shanghaichuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.JMap;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.service.CodeService;
import com.shanghaichuangshi.util.Util;

import java.io.*;
import java.util.*;

public class CodeController extends Controller {

    private final CodeService codeService = new CodeService();
    private static Engine engine = Engine.create("engine");

    @ActionKey(Url.CODE_LIST)
    public void list() {
        JSONObject jsonObject = getParameterJSONObject();

        String table_schema = jsonObject.getString("table_schema");

        List<Record> tableList = codeService.listTable(table_schema);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (Record record : tableList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("table_name", record.getStr("table_name"));
            list.add(map);
        }

        renderSuccessJson(list.size(), list);
    }

    @ActionKey(Url.CODE_SAVE)
    public void save() throws IOException {
        JSONObject jsonObject = getParameterJSONObject();

        String table_name = jsonObject.getString("table_name");
        String table_schema = jsonObject.getString("table_schema");

        List<Record> codeList = codeService.listColumn(table_name, table_schema);

        List<Record> columnList = new ArrayList<Record>();

        for (Record record : codeList) {
            if (!record.getStr("column_name").startsWith("system_")) {
                if (Util.isNullOrEmpty(record.get("character_maximum_length"))) {
                    String length = record.getStr("column_type").replace(record.get("data_type").toString(), "").replace("(", "").replace(")", "");

                    if (length.equals("") || length.contains(",")) {
                        length = "0";
                    }

                    record.set("character_maximum_length", length);
                }

                record.set("first_column_name", record.getStr("column_name").substring(0, 1).toUpperCase() + record.getStr("column_name").substring(1));
                record.set("data_type", record.getStr("data_type").toUpperCase());
                record.set("upper_column_name", record.getStr("column_name").toUpperCase());

                columnList.add(record);
            }
        }

        String lowerModelName = table_name.replace("table_", "").toLowerCase();
        String upperModelName = lowerModelName.toUpperCase();
        String firstModelName = lowerModelName.substring(0, 1).toUpperCase() + lowerModelName.substring(1);
        String firstLowerModelName = lowerModelName.substring(0, 1).toLowerCase() + lowerModelName.substring(1);

//        if (firstModelName.contains("_")) {
//            int index = firstModelName.indexOf("_");
//            firstModelName = firstModelName.substring(0, index) + firstModelName.substring(index + 1, index + 2).toUpperCase() + firstModelName.substring(index + 2);
//        }

        firstModelName = check(firstModelName);
        firstLowerModelName = check(firstLowerModelName);


        engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/");

        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "url.template", "Url.java");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "model.template", firstModelName + ".java");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "dao.template", firstModelName + "Dao.java");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "service.template", firstModelName + "Service.java");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "controller.template", firstModelName + "Controller.java");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "config.template", "WebConfig.java");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "state.template", lowerModelName + ".js");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "index.template", firstModelName + "Index.js");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "detail.template", firstModelName + "Detail.js");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "router.template", "Router.js");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "app.template", "index.js");
        write(engine, lowerModelName, upperModelName, firstModelName, firstLowerModelName, columnList, "sql.template", firstModelName + ".sql");

        renderSuccessJson("");
    }

    private String check(String name) {
        if (name.contains("_")) {
            int index = name.indexOf("_");
            name = name.substring(0, index) + name.substring(index + 1, index + 2).toUpperCase() + name.substring(index + 2);

            if (name.contains("_")) {
                name = check(name);
            }
        }

        return name;
    }

    private void write(Engine engine, String lower_model_name, String upper_model_name, String first_model_name, String firstLowerModelName, List<Record> columnList, String templateName, String fileName) throws IOException {
        JMap map = JMap.create();
        map.put("lower_model_name", lower_model_name);
        map.put("upper_model_name", upper_model_name);
        map.put("first_model_name", first_model_name);
        map.put("first_lower_model_name", firstLowerModelName);
        map.put("columnList", columnList);

        Template template = engine.getTemplate(templateName);

        String result = template.renderToString(map);

        Writer writer = new FileWriter(new File("/Users/yongqiangzhong/Documents/Publish/" + fileName), false);
        writer.write(result.toCharArray());
        writer.close();
    }

}
