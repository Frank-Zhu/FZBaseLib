package com.frankzhu.appnetworklibrary.callback;


import com.frankzhu.appbaselibrary.log.FZLogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onResponse(Call<T> call, Response<T> response) {
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        FZLogUtils.e(TAG, "failure--> url = " + call.request().url());
        t.printStackTrace();
    }
}
