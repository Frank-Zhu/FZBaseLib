package com.frankzhu.appnetworklibrary.model.base;

import com.frankzhu.appnetworklibrary.api.HttpApiMethods;
import com.frankzhu.appnetworklibrary.cache.CacheDatabase;

import rx.Subscriber;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/12 上午10:30
 * Description: 数据缓存模型基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/12      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseCacheModel<T> extends BaseModel {
    protected CacheDatabase mCacheDatabase;

    public BaseCacheModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
        mCacheDatabase = CacheDatabase.getInstance();
    }

    protected abstract void loadCacheData(Subscriber<T> subscriber);//加载缓存数据

    protected abstract void loadNetworkData(Subscriber<T> subscriber);//加载网络数据
}
