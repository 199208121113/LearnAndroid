package com.youloft.xcore.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

/**
 * String工具类
 * Created by xdf on 16-8-19.
 */
public class StringUtil {

    public static String getFileName(String path) {
        if (path == null || path.trim().length() == 0) {
            return "null";
        }
        path = path.replaceAll("\\\\", "/");
        final int pos = path.lastIndexOf("/");
        if (pos < 0) {
            return path;
        }

        return path.substring(pos + 1);
    }

    public static String toLowerCase(String str) {
        if (isEmpty(str)) {
            return "";
        }

        return str.toLowerCase(Locale.getDefault());
    }

    public static String toUpperCase(String str) {
        if (isEmpty(str)) {
            return "";
        }

        return str.toUpperCase(Locale.getDefault());
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() > 0;
    }

    /**
     * 字符串编码
     * @param value 编码对象
     * @param chartName 编码格式
     * @return 编码后的字符串
     */
    public static String decode(String value,String chartName){
        if(StringUtil.isEmpty(value)){
            return value;
        }
        String result;
        try {
            result = URLDecoder.decode(value,chartName);
        }catch (UnsupportedEncodingException e){
            result = value;
        }
        if(StringUtil.isEmpty(result)){
            result = value;
        }
        return result;
    }
}
