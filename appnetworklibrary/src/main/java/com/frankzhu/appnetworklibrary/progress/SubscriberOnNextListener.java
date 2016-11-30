package com.frankzhu.appnetworklibrary.progress;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/3 下午4:12
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/3      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);

    void onError(Throwable e);
}
