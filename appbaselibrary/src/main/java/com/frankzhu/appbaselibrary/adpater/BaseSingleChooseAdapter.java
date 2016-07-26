package com.frankzhu.appbaselibrary.adpater;

import android.content.Context;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/6/24 上午11:26
 * Description: 单选数据列表适配器
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/6/24      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseSingleChooseAdapter<T> extends FZBaseAbstractAdapter<T> {
    protected int mCurrentSelectPosition = -1;//当前选中的分类信息

    public BaseSingleChooseAdapter(Context context) {
        super(context);
    }

    public void setCurrentSelectPosition(int currentSelectPosition) {
        if (mCurrentSelectPosition != currentSelectPosition) {
            notifyItemChanged(mCurrentSelectPosition);
            mCurrentSelectPosition = currentSelectPosition;
            notifyItemChanged(mCurrentSelectPosition);
        }
    }

    public int getCurrentSelectPosition() {
        return mCurrentSelectPosition;
    }
}
