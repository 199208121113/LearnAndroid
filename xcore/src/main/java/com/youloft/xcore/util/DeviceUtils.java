package com.youloft.xcore.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * 设备相关工具类
 * Created by xdf on 16-9-5.
 */
public class DeviceUtils {
    private DeviceUtils(){
        throw new UnsupportedOperationException("no no no,the way is static !!!");
    }

    /**
     * 获取设备Mac地址吃
     *
     * <p>需添加权限{@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @param context 上下文
     * @return        设备mac地址
     */
    public static String getMacAddress(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String macAddress = info.getMacAddress().replace(":","");
        return macAddress == null ? "" : macAddress;
    }


    /**
     * 获取设备MAC地址
     *
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @return MAC地址
     */
    public static String getMacAddress() {
        String macAddress = null;
        LineNumberReader reader = null;
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            reader = new LineNumberReader(ir);
            macAddress = reader.readLine().replace(":", "");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return macAddress == null ? "" : macAddress;
    }

    /**
     * 获取设备厂商 example：Xiaomi
     *
     * @return 设备厂商
     */
    public static String getManufacturer(){
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备型号 example：MI2SC
     *
     * @return 设备型号
     */
    public static String getModel(){
        String model = Build.MODEL;
        if(model != null){
            model = model.trim().replaceAll("\\s","");
        }else {
            model = "";
        }
        return model;
    }


}
