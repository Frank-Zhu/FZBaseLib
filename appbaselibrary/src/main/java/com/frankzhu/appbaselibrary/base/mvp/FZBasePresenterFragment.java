package com.frankzhu.appbaselibrary.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frankzhu.appbaselibrary.base.FZBaseViewPagerFragment;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/9 下午1:49
 * Description: mvp 架构 Fragment 基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/9      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBasePresenterFragment<T extends IBasePresenter> extends FZBaseViewPagerFragment {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    protected abstract T createPresenter();
}
