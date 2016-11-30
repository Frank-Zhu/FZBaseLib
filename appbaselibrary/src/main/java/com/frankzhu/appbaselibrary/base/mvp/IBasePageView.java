package com.frankzhu.appbaselibrary.base.mvp;

import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/4/29 下午3:35
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/4/29      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public interface IBasePageView<T> {
    void showListSuccess(ArrayList<T> list, boolean isRefresh);//显示获取列表信息成功

    void showListError(String errorMsg);//显示获取列表错误信息
}
