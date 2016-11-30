package com.frankzhu.appbaselibrary.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frankzhu.appbaselibrary.base.FZBaseToolBarActivity;

import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/11 下午3:04
 * Description:同一页面多Presenter处理 ActionBarActivity 基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/11      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBaseMultiplePresenterToolBarActivity extends FZBaseToolBarActivity {

    protected ArrayList<BasePresenter> mPresenters;

    protected abstract ArrayList<BasePresenter> createPresenters();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenters = createPresenters();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenters != null && mPresenters.size() > 0) {
            for (BasePresenter presenter : mPresenters) {
                presenter.unSubscribe();
            }
        }
    }

}
