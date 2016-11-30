package com.frankzhu.appbaselibrary.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frankzhu.appbaselibrary.base.FZBaseActionBarActivity;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/11 下午3:04
 * Description: mvp 架构 ActionBarActivity 基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/11      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBasePresenterActionBarActivity<T extends IBasePresenter> extends FZBaseActionBarActivity {

    protected T mPresenter;

    protected abstract T createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }
}
