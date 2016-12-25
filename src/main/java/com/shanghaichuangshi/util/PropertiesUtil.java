package com.shanghaichuangshi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properties;

    public static void load(String name) {
        properties = new Properties();

        InputStream inputStream = null;

        try {
            inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);

            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("IOException: ", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("IOException: ", e);
            }
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            throw new RuntimeException("Properties is null");
        }

        return properties.getProperty(key);
    }

}
