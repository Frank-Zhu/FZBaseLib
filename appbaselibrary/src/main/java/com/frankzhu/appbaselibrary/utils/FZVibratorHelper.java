package com.frankzhu.appbaselibrary.utils;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/27  下午2:37.
 * Description: 手机震动工具类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/27        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZVibratorHelper {
    /**
     * 开启手机震动
     *
     * @param activity     调用该方法的activity实例
     * @param milliseconds 震动的时长，单位是毫秒
     */
    public static void onVibrate(Activity activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    /**
     * 开启手机震动
     *
     * @param activity 调用该方法的activity实例
     * @param pattern  自定义震动模式，数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
     * @param isRepeat 是否反复震动,如果是true,反复震动，如果是false，只震动一次
     */
    public static void onVibrate(Activity activity, long[] pattern, boolean isRepeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
    }

}
