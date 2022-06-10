package com.hiwei.test.agent.utils;

public class StringUtils {
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }
}
