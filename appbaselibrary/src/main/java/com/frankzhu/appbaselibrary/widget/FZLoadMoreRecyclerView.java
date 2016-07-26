package com.frankzhu.appbaselibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/26  下午4:40.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZLoadMoreRecyclerView extends RecyclerView {
    public enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID
    }

    private boolean isLoadingEnable = false;
    private boolean isLoading = false;
    private OnLoadNextListener onLoadNextListener;
    private OnScrollListener onScrollListener;
    private LAYOUT_MANAGER_TYPE layoutManagerType;

    public void setOnLoadNextListener(OnLoadNextListener onLoadNextListener) {
        this.onLoadNextListener = onLoadNextListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void setLoadingEnable(boolean loadingEnable) {
        isLoadingEnable = loadingEnable;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public FZLoadMoreRecyclerView(Context context) {
        super(context);
    }

    public FZLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FZLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canScrollVertically(int direction) {
        if (direction < 1) {
            boolean original = super.canScrollVertically(direction);
            return !original && getChildAt(0) != null && getChildAt(0).getTop() < 0 || original;
        }
        return super.canScrollVertically(direction);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        try {
            super.onScrollChanged(l, t, oldl, oldt);

            LayoutManager layoutManager = getLayoutManager();
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();

            int firstVisibleItemPosition = -1;
            if (layoutManagerType == null) {
                if (layoutManager instanceof LinearLayoutManager) {
                    layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR;
                } else {
                    throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager");
                }
            }

            switch (layoutManagerType) {
                case LINEAR:
                    firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    break;
                case GRID:
                    firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    break;
            }

            if (firstVisibleItemPosition + visibleItemCount >= totalItemCount &&
                    totalItemCount != 0 && isLoadingEnable && !isLoading && onLoadNextListener != null) {
                isLoading = true;
                onLoadNextListener.onLoadNext();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (dy > 0) {
            if (onScrollListener != null) {
                onScrollListener.onScrollUp();
            }
        } else {
            if (onScrollListener != null) {
                onScrollListener.onScrollDown();
            }
        }
    }

    public interface OnLoadNextListener {
        void onLoadNext();
    }

    public interface OnScrollListener {
        void onScrollUp();

        void onScrollDown();
    }

}
