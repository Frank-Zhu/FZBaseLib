package com.frankzhu.appbaselibrary.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2016/6/15  11:46.
 * Description: 空ViewHolder
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2016/6/15        ZhuWenWu           1.0                    1.0
 * Why & What is modified:
 */
public class SpaceViewHolder extends RecyclerView.ViewHolder {

    public SpaceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
