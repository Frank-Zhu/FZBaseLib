package com.frankzhu.appbaselibrary.base;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frankzhu.appbaselibrary.R;
import com.frankzhu.appbaselibrary.widget.FZDividerItemDecoration;
import com.frankzhu.appbaselibrary.widget.FZLoadMoreRecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/26  下午4:30.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBasePullRecycleRefreshFragment extends FZBasePullRefreshFragment {
    protected FZLoadMoreRecyclerView mFZLoadMoreRecyclerView;
    protected boolean isShowDivider;

    protected abstract void onLoadNextPage();

    @Override
    protected View getFragmentContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_base_recycle_view, container, false);
    }

    @Override
    protected void setUpViewComponent(View rootView) {
        mFZLoadMoreRecyclerView = (FZLoadMoreRecyclerView) rootView.findViewById(R.id.recycler_view);
        mFZLoadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (isShowDivider) {
            mFZLoadMoreRecyclerView.addItemDecoration(new FZDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        }
        mFZLoadMoreRecyclerView.setOnLoadNextListener(new FZLoadMoreRecyclerView.OnLoadNextListener() {
            @Override
            public void onLoadNext() {
                onLoadNextPage();
            }
        });
    }

    @Override
    protected void onLazyLoad() {

    }
}
