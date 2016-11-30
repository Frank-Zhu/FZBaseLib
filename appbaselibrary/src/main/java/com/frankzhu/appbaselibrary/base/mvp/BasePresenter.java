package com.frankzhu.appbaselibrary.base.mvp;

import rx.subscriptions.CompositeSubscription;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/4/30 下午12:26
 * Description: Presenter 基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/4/30      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BasePresenter implements IBasePresenter {
    protected CompositeSubscription mSubscriptions;
    protected final String TAG = getClass().getSimpleName();

    public BasePresenter() {
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }
}
