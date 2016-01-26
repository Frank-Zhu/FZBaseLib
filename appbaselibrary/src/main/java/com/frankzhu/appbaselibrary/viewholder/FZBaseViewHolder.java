package com.frankzhu.appbaselibrary.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/26  下午6:31.
 * Description: ViewHolder基类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBaseViewHolder<T> extends RecyclerView.ViewHolder {

    public FZBaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }

    public abstract void bindViewData(T data);
}
