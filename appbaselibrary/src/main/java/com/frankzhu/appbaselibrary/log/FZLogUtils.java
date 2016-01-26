package com.frankzhu.appbaselibrary.log;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/26  下午2:33.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZLogUtils {
    private static boolean DEBUG_V = true;
    private static boolean DEBUG_D = true;
    private static boolean DEBUG_I = true;
    private static boolean DEBUG_W = true;
    private static boolean DEBUG_E = true;

    /**
     * 设置LOG输出级别
     *
     * @param level 0,1,2,3,4  0-->全部输出
     */
    public static void setLogLevel(int level) {
        DEBUG_V = level >= 0;
        DEBUG_D = level >= 1;
        DEBUG_I = level >= 2;
        DEBUG_W = level >= 3;
        DEBUG_E = level >= 4;
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (DEBUG_V) {
            android.util.Log.v(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG_D) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG_D) {
            android.util.Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG_I) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (DEBUG_I) {
            android.util.Log.i(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (DEBUG_W) {
            android.util.Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (DEBUG_W) {
            android.util.Log.w(tag, tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUG_E) {
            android.util.Log.e(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG_E) {
            android.util.Log.e(tag, msg);
        }
    }
}
