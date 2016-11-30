package com.frankzhu.appbaselibrary.base.mvp;

import rx.Subscriber;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/16 下午3:03
 * Description: 列表基础 Model
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public interface IBasePageModel<T, D> {
    void loadList(Subscriber<T> subscriber, boolean isRefresh, D param);//获取用户抵扣券列表信息
}
