package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Code;
import com.shanghaichuangshi.service.CodeService;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CodeController extends Controller {

    private final CodeService codeService = new CodeService();

    @Path(Url.CODE_LIST)
    public void list() {
        Code codeModel = getModel(Code.class);

        List<Code> tableList = codeService.listTable(codeModel.getTable_name());

        renderJson(tableList.size(), tableList);
    }

    @Path(Url.CODE_SAVE)
    public void save() throws IOException {
        Code codeModel = getModel(Code.class);

        codeModel.validate(Code.TABLE_NAME);

        String table_name = codeModel.getTable_name();

        List<Code> codeList = codeService.listColumn(codeModel.getTable_name());

        List<Code> columnList = new ArrayList<Code>();

        for (Code code : codeList) {
            if (! code.getColumn_name().startsWith("system_")) {
                code.set("first_column_name", code.getColumn_name().substring(0, 1).toUpperCase() + code.getColumn_name().substring(1));

                String length = code.getString("column_type").replace(code.get("data_type").toString(), "").replace("(", "").replace(")", "");

                if (length.equals("")) {
                    length = "0";
                }

                code.set("character_maximum_length", length);

                columnList.add(code);
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

        renderJson("");
    }

    private void write(String lowerModelName, String upperModelName, String firstModelName, List<Code> columnList, String templateName, String fileName) throws IOException {
        String root = System.getProperty("user.dir") + File.separator + "/src/main/resources/template";
        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        Template template = groupTemplate.getTemplate(templateName);
        template.binding("lowerModelName", lowerModelName);
        template.binding("upperModelName", upperModelName);
        template.binding("firstModelName", firstModelName);
        template.binding("columnList", columnList);

        template.renderTo(new OutputStreamWriter(new FileOutputStream(new File("/Users/yongqiangzhong/Documents/Publish/" + fileName)), "UTF-8"));
    }

}
