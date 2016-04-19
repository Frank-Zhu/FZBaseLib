package com.frankzhu.appnetworklibrary.callback;

import retrofit2.Call;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/2/29  上午11:23.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/2/29        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public interface FZDataCallBackListener<T> {

    /**
     * HTTP 请求成功回调
     *
     * @param data 返回的数据
     */
    void onResponse(Call<T> call, T data);

    /**
     * HTTP 请求失败回调
     *
     * @param isNetError 是否网络错误 true 没有网络连接
     * @param t          Throwable
     */
    void onFailure(boolean isNetError, Throwable t);
}
