package com.frankzhu.appnetworklibrary.model.base;

import com.frankzhu.appbaselibrary.base.mvp.IBasePageModel;
import com.frankzhu.appnetworklibrary.api.ApiException;
import com.frankzhu.appnetworklibrary.api.HttpApiMethods;

import rx.Subscriber;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/26 下午3:59
 * Description: 默认多页面数据请求实现
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/26      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseDefaultPageModel<T, D> extends BasePageModel<T> implements IBasePageModel<T, D> {
    protected D param;

    public BaseDefaultPageModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
    }

    @Override
    public void loadList(Subscriber<T> subscriber, boolean isRefresh, D param) {
        this.param = param;
        if (isRefresh) {//刷新数据
            if (isOnline()) {//有网络加载网络数据
                refreshData(subscriber);
            } else {//没有网络，加载缓存数据
                subscriber.onError(new ApiException(HttpApiMethods.NETWORK_ERROR_MSG));
            }
        } else {//加载更多数据
            loadMoreData(subscriber);
        }
    }
}
