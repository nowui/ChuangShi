package com.shanghaichuangshi.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {

    public static String readData(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            StringBuilder result = new StringBuilder();
            br = request.getReader();
            for (String line = null; (line = br.readLine()) != null; ) {
                result.append(line).append("\n");
            }

            return result.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e);
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException: " + e);
                }
        }
    }

}