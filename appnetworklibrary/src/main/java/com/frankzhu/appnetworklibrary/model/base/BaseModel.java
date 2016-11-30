package com.frankzhu.appnetworklibrary.model.base;

import com.frankzhu.appbaselibrary.app.FZBaseApplication;
import com.frankzhu.appbaselibrary.utils.FZNetworkInfoHelper;
import com.frankzhu.appnetworklibrary.api.HttpApiMethods;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/4/30 下午12:29
 * Description: 模型基类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/4/30      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BaseModel {
    protected HttpApiMethods httpApiMethods;

    public BaseModel(HttpApiMethods httpApiMethods) {
        this.httpApiMethods = httpApiMethods;
    }

    protected boolean isOnline() {//有网络加载网络数据
        return FZNetworkInfoHelper.isOnline(FZBaseApplication.getContext());
    }

}
