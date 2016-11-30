package com.frankzhu.appnetworklibrary.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.frankzhu.appbaselibrary.utils.FZStringHelper;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/3 下午4:10
 * Description: 加载进度ProgressDialog控制类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/3      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog pd;
    private String message;
    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener progressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        mProgressCancelListener = progressCancelListener;
        this.cancelable = cancelable;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private void initProgressDialog() {
        if (pd == null) {
            pd = new ProgressDialog(context);

            pd.setCancelable(cancelable);
            if (FZStringHelper.notEmpty(message)) {
                pd.setMessage(message);
            } else {
                pd.setMessage("正在加载数据");
            }
            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
