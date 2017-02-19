package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Code;
import com.shanghaichuangshi.service.CodeService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CodeController extends Controller {

    private static final CodeService codeService = new CodeService();

    @ActionKey(Url.CODE_LIST)
    public void list() {
        Code codeModel = getParameter(Code.class);

        List<Record> tableList = codeService.listTable(codeModel.getTable_name());

        renderSuccessJson(tableList.size(), tableList);
    }

    @ActionKey(Url.CODE_SAVE)
    public void save() throws IOException {
        Code codeModel = getParameter(Code.class);

        codeModel.validate(Code.TABLE_NAME);

        String table_name = codeModel.getTable_name();

        List<Record> codeList = codeService.listColumn(codeModel.getTable_name());

        List<Record> columnList = new ArrayList<Record>();

        for (Record record : codeList) {
            if (! record.getStr("column_name").startsWith("system_")) {
                record.set("first_column_name", record.getStr("column_name").substring(0, 1).toUpperCase() + record.getStr("column_name").substring(1));

                String length = record.getStr("column_type").replace(record.get("data_type").toString(), "").replace("(", "").replace(")", "");

                if (length.equals("") || length.contains(",")) {
                    length = "0";
                }

                record.set("character_maximum_length", length);

                columnList.add(record);
            }
        }

        String lowerModelName = table_name.replace("table_", "").toLowerCase();
        String upperModelName = lowerModelName.toUpperCase();
        String firstModelName = lowerModelName.substring(0, 1).toUpperCase() + lowerModelName.substring(1);

        write(lowerModelName, upperModelName, firstModelName, columnList, "/url.template", "Url.java");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/model.template", firstModelName + ".java");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/dao.template", firstModelName + "Dao.java");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/service.template", firstModelName + "Service.java");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/controller.template", firstModelName + "Controller.java");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/config.template", "WebConfig.java");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/state.template", lowerModelName + ".js");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/index.template", firstModelName + "Index.js");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/detail.template", firstModelName + "Detail.js");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/router.template", "Router.js");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/app.template", "index.js");

        renderSuccessJson("");
    }

    private void write(String lowerModelName, String upperModelName, String firstModelName, List<Record> columnList, String templateName, String fileName) throws IOException {
//        String root = System.getProperty("user.dir") + File.separator + "/src/main/resources/template";
//        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
//        Configuration configuration = Configuration.defaultConfiguration();
//        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
//        Template template = groupTemplate.getTemplate(templateName);
//        template.binding("lowerModelName", lowerModelName);
//        template.binding("upperModelName", upperModelName);
//        template.binding("firstModelName", firstModelName);
//        template.binding("columnList", columnList);
//
//        template.renderTo(new OutputStreamWriter(new FileOutputStream(new File("/Users/yongqiangzhong/Documents/Publish/" + fileName)), "UTF-8"));

        Engine engine = Engine.create("engine");
        engine.setBaseTemplatePath(PathKit.getRootClassPath() + "/template/");
        Template template = engine.getTemplate(templateName);
    }

}
