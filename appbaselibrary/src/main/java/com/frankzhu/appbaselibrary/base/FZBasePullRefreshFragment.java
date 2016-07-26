package com.frankzhu.appbaselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frankzhu.appbaselibrary.R;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/26  下午4:12.
 * Description: 下拉刷新View Fragment
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBasePullRefreshFragment extends FZBaseViewPagerFragment implements SwipeRefreshLayout.OnRefreshListener {
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    protected abstract View getFragmentContentView(LayoutInflater inflater, ViewGroup container);

    protected abstract void setUpViewComponent(View rootView);

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_base_pull_refresh;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        setUpSwipeRefreshLayout(rootView, inflater, container);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewComponent(view);
    }

    private void setUpSwipeRefreshLayout(View rootView, LayoutInflater inflater, ViewGroup container) {
        if (rootView != null) {
            mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
            mSwipeRefreshLayout.addView(getFragmentContentView(inflater, container));
            mSwipeRefreshLayout.setOnRefreshListener(this);
            mSwipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_light,
                    R.color.holo_green_light, R.color.holo_orange_light,
                    R.color.holo_red_light);
        }
    }
}
