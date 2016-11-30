package com.frankzhu.appnetworklibrary.model.base;

import com.frankzhu.appnetworklibrary.api.HttpApiMethods;
import com.frankzhu.appnetworklibrary.cache.CacheDatabase;

import rx.Subscriber;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/12 上午10:20
 * Description: 分页数据缓存模型基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/12      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseCachePageModel<T> extends BasePageModel<T> {
    protected CacheDatabase mCacheDatabase;

    public BaseCachePageModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
        mCacheDatabase = CacheDatabase.getInstance();
    }

    protected abstract void loadCacheData(Subscriber<T> subscriber);//加载缓存数据
}
