package com.frankzhu.appbaselibrary.base;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.frankzhu.appbaselibrary.R;
import com.frankzhu.appbaselibrary.utils.FZSharedPreferencesHelper;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/25  上午11:20.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/25        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZBaseActionBarActivity extends AppCompatActivity {
    protected String TAG = FZBaseActionBarActivity.class.getSimpleName();
    protected ActionBar mActionBar;
    protected FZSharedPreferencesHelper mFZSharedPreferencesHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFZSharedPreferencesHelper = FZSharedPreferencesHelper.getInstance();
        mActionBar = getSupportActionBar();
        showBackActionBar();
    }

    /**
     * 显示返回按钮 <-
     */
    protected void showBackActionBar() {
        showBackActionBar(android.R.color.white);
    }

    /**
     * 显示返回按钮 <-
     *
     * @param color 按钮颜色
     */
    protected void showBackActionBar(int color) {
        showActionBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_material, null);
            if (drawable != null) {
                drawable.setTint(getResources().getColor(color));
            }
            mActionBar.setHomeAsUpIndicator(drawable);
        } else {
            Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
            if (drawable != null) {
                drawable.setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
            }
            mActionBar.setHomeAsUpIndicator(drawable);
        }
    }

    /**
     * 显示关闭按钮 X
     */
    protected void showXActionBar() {
        showXActionBar(android.R.color.white);
    }

    /**
     * 显示关闭按钮 X
     *
     * @param color 按钮颜色
     */
    protected void showXActionBar(int color) {
        showActionBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_clear_material, null);
            if (drawable != null) {
                drawable.setTint(getResources().getColor(color));
            }
            if (mActionBar != null) {
                mActionBar.setHomeAsUpIndicator(drawable);
            }
        } else {
            Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_clear_material);
            if (drawable != null) {
                drawable.setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
            }
            if (mActionBar != null) {
                mActionBar.setHomeAsUpIndicator(drawable);
            }
        }
    }

    protected void showActionBar() {
        if (mActionBar != null) {
            mActionBar.setDisplayShowCustomEnabled(false);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void hideHomeUp() {
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
