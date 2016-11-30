package com.frankzhu.appnetworklibrary.model.base;

import com.frankzhu.appnetworklibrary.api.AppApiContact;
import com.frankzhu.appnetworklibrary.api.HttpApiMethods;

import rx.Subscriber;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/12 上午10:20
 * Description: 分页数据模型基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/12      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BasePageModel<T> extends BaseModel {
    protected int currentPage = AppApiContact.DEFAULT_CURRENT_PAGE;

    public BasePageModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isRefresh() {
        return currentPage == AppApiContact.DEFAULT_CURRENT_PAGE;
    }

    protected abstract void refreshData(Subscriber<T> subscriber);//加载最新数据

    protected abstract void loadMoreData(Subscriber<T> subscriber);//加载更多数据

    protected abstract void loadNetworkData(Subscriber<T> subscriber);//加载网络数据
}
