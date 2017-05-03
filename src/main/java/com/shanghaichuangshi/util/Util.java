package com.shanghaichuangshi.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import com.shanghaichuangshi.constant.Constant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }

        return false;
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static boolean isEmail(String str) {
        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean isPhone(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean isDate(String str) {
        String regex = "^((((1[6-9]|[2-9]//d)//d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]//d|3[01]))|(((1[6-9]|[2-9]//d)//d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]//d|30))|(((1[6-9]|[2-9]//d)//d{2})-0?2-(0?[1-9]|1//d|2[0-8]))|(((1[6-9]|[2-9]//d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?//d):[0-5]?//d:[0-5]?//d$";
        return match(regex, str);
    }

    public static boolean isNumber(String str) {
        String regex = "^[0-9]*$";
        return match(regex, str);
    }

    public static boolean isIntNumber(String str) {
        String regex = "^//+?[1-9][0-9]*$";
        return match(regex, str);
    }

    public static boolean isDecimal(String str) {
        String regex = "^[0-9]+(.[0-9]{2})?$";
        return match(regex, str);
    }

    public static boolean match(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static Object[][] getObjectArray(List<Object[]> parameterList) {
        Object[][] parameterArray = new Object[parameterList.size()][];
        int i = 0;
        for (Object[] oo : parameterList) {
            parameterArray[i++] = oo;
        }

        return parameterArray;
    }

    public static String getFixLenthString(int strLength) {
        Random rm = new Random();

        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        String fixLenthString = String.valueOf(pross);

        return fixLenthString.substring(1, strLength + 1);
    }

    public static String get() {
        List<String> list = new ArrayList<String>();
        List<JSONObject> provinceList = new ArrayList<JSONObject>();
        List<JSONObject> cityList = new ArrayList<JSONObject>();
        List<JSONObject> areaList = new ArrayList<JSONObject>();

        try {
            String encoding = "UTF-8";
            File file = new File(PathKit.getWebRootPath() + "/WEB-INF/classes/china.txt");
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        for (String lineTxt : list) {
            if (lineTxt.contains("province")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", lineTxt.split("province")[0]);
                jsonObject.put("label", lineTxt.split("province")[1]);
                provinceList.add(jsonObject);
            }

            if (lineTxt.contains("city")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", lineTxt.split("city")[0]);
                jsonObject.put("label", lineTxt.split("city")[1]);
                cityList.add(jsonObject);
            }

            if (lineTxt.contains("area")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", lineTxt.split("area")[0]);
                jsonObject.put("label", lineTxt.split("area")[1]);
                areaList.add(jsonObject);
            }
        }

        for (JSONObject city : cityList) {
            List<JSONObject> children = new ArrayList<JSONObject>();

            for (JSONObject area : areaList) {
                if (city.getString("value").substring(0, 4).equals(area.getString("value").substring(0, 4))) {
                    if (!area.getString("label").equals("市辖区")) {
                        children.add(area);
                    }
                }
            }

            city.put("children", children);
        }

        for (JSONObject province : provinceList) {
            List<JSONObject> children = new ArrayList<JSONObject>();

            for (JSONObject city : cityList) {
                if (province.getString("value").substring(0, 2).equals(city.getString("value").substring(0, 2))) {
                    if (city.getString("label").equals("市辖区")) {
                        city.put("label", province.getString("label"));
                    }

                    children.add(city);
                }
            }

            province.put("children", children);

            province.put("label", province.getString("label").replace("市", ""));
        }

        return JSONArray.toJSONString(provinceList);
    }

    public static String getThumbnail_path(String path) {
        return path.substring(0, path.lastIndexOf("/")) + "/" + Constant.THUMBNAIL + "/" + path.substring(path.lastIndexOf("/") + 1, path.length());
    }

    public static String getOriginal_path(String path) {
        return path.substring(0, path.lastIndexOf("/")) + "/" + Constant.ORIGINAL + "/" + path.substring(path.lastIndexOf("/") + 1, path.length());
    }

}
