package com.frankzhu.appbaselibrary.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.frankzhu.appbaselibrary.utils.FZSharedPreferencesHelper;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/26  下午5:32.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZBaseApplication extends MultiDexApplication {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        setUpSharedPreferencesHelper(getApplicationContext());//初始化SharedPreferences
    }

    /**
     * 初始化SharedPreferences
     *
     * @param context 上下文
     */
    private void setUpSharedPreferencesHelper(Context context) {
        FZSharedPreferencesHelper.getInstance().Builder(context);
    }

    public static Context getContext() {
        return sContext;
    }
}
