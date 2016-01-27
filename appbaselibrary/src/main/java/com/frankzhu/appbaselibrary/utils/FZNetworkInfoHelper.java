package com.frankzhu.appbaselibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/27  下午12:05.
 * Description: 网络相关工具类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/27        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZNetworkInfoHelper {
    /**
     * 网络是否可用
     *
     * @param context 上下文
     * @return true 可用 false 不可用
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Network[] networks;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                networks = mgr.getAllNetworks();
                for (Network network : networks) {
                    NetworkInfo networkInfo = mgr.getNetworkInfo(network);
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            } else {
                NetworkInfo[] info = mgr.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取设备网络类型
     *
     * @param context 上下文
     * @return 网络类型
     */
    public static String getCurrentNetType(Context context) {
        String type = "UNKNOWN";
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            type = "UNKNOWN";
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = "WIFI";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = "2G";
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = "3G";
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                type = "4G";
            }
        }
        return type;
    }

    /**
     * 是否2G网络
     *
     * @param context 上下文
     * @return true:当前网络为2G网络
     */
    public static boolean is2GNetwork(Context context) {
        return getCurrentNetType(context).equalsIgnoreCase("2G");
    }

    /**
     * 是否3G网络
     *
     * @param context 上下文
     * @return true:当前网络为3G网络
     */
    public static boolean is3GNetwork(Context context) {
        return getCurrentNetType(context).equalsIgnoreCase("3G");
    }

    /**
     * 是否4G网络
     *
     * @param context 上下文
     * @return true:当前网络为4G网络
     */
    public static boolean is4GNetwork(Context context) {
        return getCurrentNetType(context).equalsIgnoreCase("4G");
    }

    /**
     * 是否WIFI网络
     *
     * @param context 上下文
     * @return true:当前网络为WIFI网络
     */
    public static boolean isWifiNetwork(Context context) {
        return getCurrentNetType(context).equalsIgnoreCase("WIFI");
    }

    /**
     * 获取设备IP地址
     *
     * @param context 上下文
     * @return IP地址
     */
    public static String getDeviceIpAddress(Context context) {
        String ipAddress = "UNKNOWN";
        if (isNetworkAvailable(context)) {
            WifiManager wifiInfo = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            DhcpInfo d = wifiInfo.getDhcpInfo();
            if (d != null) {
                ipAddress = String.valueOf(d.ipAddress);
            }
        }
        return ipAddress;
    }
}
