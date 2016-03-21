package com.frankzhu.appnetworklibrary.callback;


import com.frankzhu.appbaselibrary.log.FZLogUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/2/29  上午11:05.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/2/29        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZHttpBaseCallback<T> implements Callback<T> {
    protected String TAG = FZHttpBaseCallback.class.getSimpleName();

    @Override
    public void success(T t, Response response) {

    }

    @Override
    public void failure(RetrofitError error) {
        FZLogUtils.e(TAG, "failure--> url = " + error.getUrl());
        error.printStackTrace();
    }
}
