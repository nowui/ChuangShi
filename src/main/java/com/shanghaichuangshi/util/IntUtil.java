package com.shanghaichuangshi.util;

public class IntUtil {

    public static boolean checkLength(Integer integer, int length) {
        System.out.println(integer + " --- " + length);
        String regex = "^.{0," + length + "}$";

        return Util.match(regex, integer.toString());
    }

}
