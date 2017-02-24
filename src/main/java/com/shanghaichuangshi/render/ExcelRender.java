package com.shanghaichuangshi.render;

import com.jfinal.render.Render;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

public class ExcelRender extends Render {

    private HSSFWorkbook wb;
    private String name;

    public  ExcelRender(HSSFWorkbook wb, String name){
        this.wb = wb;
        this.name = name;
    }

    public void render() {
        try {
            response.reset();
            response.addHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(name, "UTF-8") + ".xls");
            response.setContentType("application/x-msdownload");

            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
