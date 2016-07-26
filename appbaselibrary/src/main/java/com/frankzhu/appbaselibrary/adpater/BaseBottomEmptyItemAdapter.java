package com.frankzhu.appbaselibrary.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frankzhu.appbaselibrary.R;
import com.frankzhu.appbaselibrary.R2;
import com.frankzhu.appbaselibrary.viewholder.FZBaseViewHolder;

import butterknife.BindView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/12 上午11:55
 * Description: 显示底部 "没有更多数据" 适配器
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/12      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseBottomEmptyItemAdapter<T> extends FZBaseAbstractMultipleItemAdapter<T> {
    private boolean isShowBottomEmpty;
    private String mEmptyMessage;

    public BaseBottomEmptyItemAdapter(Context context) {
        super(context);
        mBottomCount = 1;
    }

    public void setShowBottomEmpty(boolean showBottomEmpty) {
        if (mBottomCount > 0) {
            isShowBottomEmpty = showBottomEmpty;
            notifyItemChanged(getItemCount() - 1);
        }
    }

    public void setEmptyMessage(String emptyMessage) {
        mEmptyMessage = emptyMessage;
    }

    /**
     * 移除所有记录
     */
    public void clearItems() {
        int size = mDataList.size();
        if (size > 0) {
            mDataList.clear();
            notifyItemRangeRemoved(mHeaderCount, size + mBottomCount);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size() == 0 ? mHeaderCount : super.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateBottomView(ViewGroup parent) {
        return new BottomEmptyViewHolder(mLayoutInflater.inflate(R.layout.include_bottom_empty, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BottomEmptyViewHolder) {
            ((BottomEmptyViewHolder) holder).bindViewData(isShowBottomEmpty ? mEmptyMessage : "");
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    static class BottomEmptyViewHolder extends FZBaseViewHolder<String> {
        @BindView(R2.id.tv_empty)
        TextView mTvEmpty;

        BottomEmptyViewHolder(View view) {
            super(view);
        }

        @Override
        public void bindViewData(String msg) {
            mTvEmpty.setText(msg);
        }
    }
}
