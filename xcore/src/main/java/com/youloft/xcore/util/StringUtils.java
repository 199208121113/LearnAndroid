package com.youloft.xcore.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

/**
 * String工具类
 * Created by xdf on 16-8-19.
 */
public class StringUtils {

    /**
     * 根据文件路径获取文件名称
     *
     * @param path  路径
     * @return      文件名称
     */
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

    /**
     * 将字符串转化成小写
     *
     * @param str 待处理字符串
     * @return    全部是小写的字符串
     */
    public static String toLowerCase(String str) {
        if (isEmpty(str)) {
            return "";
        }

        return str.toLowerCase(Locale.getDefault());
    }

    /**
     * 将字符串转化成大写
     *
     * @param str 待处理字符串
     * @return    全部大写的字符串
     */
    public static String toUpperCase(String str) {
        if (isEmpty(str)) {
            return "";
        }

        return str.toUpperCase(Locale.getDefault());
    }

    /**
     * 判断字符串是否为空
     *
     * @param s 待处理字符串
     * @return  {@code true} 字符串不为空 {@code}:false 字符串为空
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() > 0;
    }

    /**
     * 字符串编码
     *
     * @param value 编码对象
     * @param chartName 编码格式
     * @return 编码后的字符串
     */
    public static String decode(String value,String chartName){
        if(StringUtils.isEmpty(value)){
            return value;
        }
        String result;
        try {
            result = URLDecoder.decode(value,chartName);
        }catch (UnsupportedEncodingException e){
            result = value;
        }
        if(StringUtils.isEmpty(result)){
            result = value;
        }
        return result;
    }

    /**
     * 判断字符串是否为空或者全为空格
     *
     * @param str 待校验字符串
     * @return {@code true}:null 或者全部空格 {@code false}:不为null且不全空格
     */
    public static boolean isSpace(String str){
        return (str == null || str.trim().length() == 0);
    }

    /**
     * 首字母大写
     *
     * @param str 待处理字符串
     * @return 首字母大写的字符串
     */
    public static String upperFirstLetter(String str){
        if(isEmpty(str) || !Character.isLowerCase(str.charAt(0))){
            return str;
        }
        return String.valueOf((char) (str.charAt(0) - 32)) + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str  待处理字符串
     * @return     首字母小写字符串
     */
    public static String lowerFirstLetter(String str){
        if(isEmpty(str) || !Character.isUpperCase(str.charAt(0))){
            return str;
        }

        return String.valueOf((char) (str.charAt(0) + 32)) + str.substring(1);
    }

}
