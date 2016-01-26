package com.frankzhu.appbaselibrary.base;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/26  下午4:08.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBaseViewPagerFragment extends FZBaseFragment {
    protected boolean isInitFinish = false;//是否已初始化

    protected abstract void onInitFragmentData();//初始化fragment数据
    
    public void initPagerFragmentData() {
        if (!isInitFinish) {
            isInitFinish = true;
            onInitFragmentData();
        }
    }

    public void setInitFinish(boolean isInitFinish) {
        this.isInitFinish = isInitFinish;
    }

}
