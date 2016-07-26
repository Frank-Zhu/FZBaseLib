package com.frankzhu.appbaselibrary.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.frankzhu.appbaselibrary.R;
import com.frankzhu.appbaselibrary.app.FZBaseApplication;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/4/29 下午4:51
 * Description: Toast 工具类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/4/29      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZToastHelper {
    public static void showToastMessage(String msg) {
        showToastMessage(msg, Toast.LENGTH_SHORT);
    }

    public static void showToastMessage(String msg, int length) {
        if (FZStringHelper.notEmpty(msg)) {
            // create instance
            Toast toast = new Toast(FZBaseApplication.getContext());
            // inflate custom view
            View view = LayoutInflater.from(FZBaseApplication.getContext()).inflate(R.layout.toast_layout, null);
            ((TextView) view.findViewById(R.id.tv_message)).setText(msg);
            // set custom view
            toast.setView(view);
            // set duration
            toast.setDuration(length);
            // set position
            int margin = FZBaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen.toast_vertical_margin);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, margin);
            // show toast
            toast.show();
        }
    }
}
