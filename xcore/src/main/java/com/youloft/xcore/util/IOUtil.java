package com.youloft.xcore.util;

import java.io.File;

/**
 * Created by xdf on 16-8-19.
 */
public class IOUtil {

    public static boolean mkDir(String path){
        if (StringUtils.isEmpty(path)){
            return false;
        }
        if(path.endsWith("/")){
            int n = path.lastIndexOf("/");
            path = path.substring(0,n);
        }
        File file = new File(path);
        if(file.exists()){
            if(file.isDirectory()){
                return true;
            }else{
                file.delete();
            }
        }
        return file.mkdirs();
    }
}
