package com.frankzhu.appnetworklibrary.model.base;

import com.frankzhu.appbaselibrary.base.mvp.IBasePageModel;
import com.frankzhu.appnetworklibrary.api.HttpApiMethods;

import rx.Subscriber;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/16 下午3:11
 * Description: 默认列表请求数据模型
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseDefaultCachePageModel<T, D> extends BaseCachePageModel<T> implements IBasePageModel<T, D> {
    protected D param;

    public BaseDefaultCachePageModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
    }

    @Override
    public void loadList(Subscriber<T> subscriber, boolean isRefresh, D param) {
        this.param = param;
        if (isRefresh) {//刷新数据
            if (isOnline()) {//有网络加载网络数据
                refreshData(subscriber);
            } else {//没有网络，加载缓存数据
                loadCacheData(subscriber);
            }
        } else {//加载更多数据
            loadMoreData(subscriber);
        }
    }
}
