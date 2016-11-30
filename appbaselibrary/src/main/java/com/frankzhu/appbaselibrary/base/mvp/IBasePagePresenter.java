package com.frankzhu.appbaselibrary.base.mvp;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/16 上午10:33
 * Description: 列表基础Presenter
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public interface IBasePagePresenter extends IBasePresenter {
    void getRefreshLists();//获取最新列表数据

    void getMoreLists();//获取更多列表数据
}
