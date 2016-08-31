package com.youloft.xcore.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Created by xdf on 16-8-19.
 */
public class FileUtil {
    public static boolean saveTextToFilePath(String fileFullPath, String txt) throws Exception {
        return saveTextToFilePath(fileFullPath,txt,false);
    }

    public static boolean saveTextToFilePath(String fileFullPath, String txt, boolean append) throws Exception {
        if (StringUtil.isEmpty(fileFullPath)) {
            throw new RuntimeException("fileFullPath is null");
        }

        File tempFile = new File(fileFullPath).getParentFile();
        if (tempFile != null && (!tempFile.exists())) {
            IOUtil.mkDir(tempFile.getAbsolutePath());
        }

        boolean writeStatus = false;
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileFullPath, append), Charset.defaultCharset()));
            bw.write(txt);
            bw.flush();
            writeStatus = true;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {//ignore}
            }
        }
        return writeStatus;
    }

    public static String getTextByFilePath(String fileFullPath) throws Exception{
        File file = new File(fileFullPath);
        if(!file.exists() || file.length() == 0){
            return "";
        }

        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),Charset.defaultCharset()));
            int rec = -1;
            char[] buf = new char[8192];
            while( (rec=br.read(buf,0,buf.length)) != -1){
                sb.append(buf,0,rec);
            }
        }finally{
            try {
                if(br != null) {
                    br.close();
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String getTextByInputStream(InputStream is, Charset charset) throws Exception{
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is,charset));
            int rec = -1;
            char[] buf = new char[8192];
            while( (rec=br.read(buf,0,buf.length)) != -1){
                sb.append(buf,0,rec);
            }
        }finally{
            try {
                if(br != null) {
                    br.close();
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
