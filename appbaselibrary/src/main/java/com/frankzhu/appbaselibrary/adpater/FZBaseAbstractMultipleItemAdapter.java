package com.frankzhu.appbaselibrary.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/26  下午5:38.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBaseAbstractMultipleItemAdapter<T> extends FZBaseAbstractAdapter<T> {
    public enum ITEM_TYPE {
        ITEM_TYPE_HEADER,
        ITEM_TYPE_BOTTOM
    }

    protected int mHeaderCount;//头部View个数
    protected int mBottomCount;//底部View个数

    public FZBaseAbstractMultipleItemAdapter(Context context) {
        super(context);
    }

    public void setHeaderCount(int headerCount) {
        this.mHeaderCount = headerCount;
    }

    public void setBottomCount(int bottomCount) {
        this.mBottomCount = bottomCount;
    }

    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + super.getItemCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_HEADER.ordinal()) {
            return onCreateHeaderView(parent);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_BOTTOM.ordinal()) {
            return onCreateBottomView(parent);
        } else {
            return onCreateContentView(parent, viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {//头部View
            return ITEM_TYPE.ITEM_TYPE_HEADER.ordinal();
        } else if (isBottomView(position)) {//底部View
            return ITEM_TYPE.ITEM_TYPE_BOTTOM.ordinal();
        } else {
            return getContentViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount + super.getItemCount() + mBottomCount;
    }

    @Override
    public T getItemData(int position) {
        int index = position - mHeaderCount;
        if (index >= super.getItemCount()) {
            return null;
        }
        return super.getItemData(index);
    }

    /**
     * 移除某一条记录
     *
     * @param position 移除数据的position 如果有Header需要减去Header数量
     */
    public void removeItem(int position) {
        if (position < mDataList.size()) {
            mDataList.remove(position);
            notifyItemRemoved(mHeaderCount + position);
        }
    }


    /**
     * 添加一条记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入数据的位置 如果有Header需要减去Header数量
     */
    public void addItem(T data, int position) {
        if (position <= mDataList.size()) {
            mDataList.add(position, data);
            notifyItemInserted(mHeaderCount + position);
        }
    }


    /**
     * 移除所有记录
     */
    public void clearItems() {
        int size = mDataList.size();
        if (size > 0) {
            mDataList.clear();
            notifyItemRangeRemoved(mHeaderCount, size);
        }
    }


    /**
     * 批量添加记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入数据的位置 如果有Header需要减去Header数量
     */
    public void addItems(List<T> data, int position) {
        if (position <= mDataList.size() && data != null && data.size() > 0) {
            mDataList.addAll(position, data);
            notifyItemRangeChanged(mHeaderCount + position, data.size());
        }
    }

    public abstract int getContentViewType(int position);

    public abstract RecyclerView.ViewHolder onCreateHeaderView(ViewGroup parent);//创建头部View

    public abstract RecyclerView.ViewHolder onCreateContentView(ViewGroup parent, int viewType);//创建中间内容View

    public abstract RecyclerView.ViewHolder onCreateBottomView(ViewGroup parent);//创建底部View
}
