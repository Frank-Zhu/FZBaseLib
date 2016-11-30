package com.frankzhu.appbaselibrary.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frankzhu.appbaselibrary.base.FZBaseViewPagerFragment;

import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/8/5 上午10:33
 * Description: 同一页面多Presenter处理 Fragment 基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/8/5      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBaseMultiplePresenterFragment extends FZBaseViewPagerFragment {
    protected ArrayList<BasePresenter> mPresenters;

    protected abstract ArrayList<BasePresenter> createPresenters();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenters = createPresenters();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenters != null && mPresenters.size() > 0) {
            for (BasePresenter presenter : mPresenters) {
                presenter.unSubscribe();
            }
        }
    }
}
