package com.youloft.xcore.util;

import android.util.Log;

/**
 * log常用工具类
 * <p/>
 * Created by xdf on 16-9-8.
 */
public class LogUtil {
    private static boolean debug = true;

    public static void setDebug(boolean debug) {
        LogUtil.debug = debug;
    }

    public static void d(String tag, String msg) {
        if (debug) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (debug) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (debug) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (debug) {
            Log.e(tag, msg);
        }
    }
}
