package com.shanghaichuangshi.util;

public class StringUtil {

    public static boolean checkLength(String string, int length) {
        String regex = "^.{0," + length + "}$";

        return Util.match(regex, string);
    }

}
